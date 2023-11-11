package com.blbd.volunteer.controller;
import com.alibaba.fastjson.JSON;
import com.blbd.volunteer.service.ChatFriendListService;
import com.blbd.volunteer.service.ChatMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@CrossOrigin("*")
@RestController
@RequestMapping("/File")
public class FileController {
    // 读取配置文件中的路径 static/files
    @Value("${fileLocation}")
    private String fileLocation;

    @Autowired
    ChatMsgService chatMsgService;
    @Autowired
    ChatFriendListService chatFriendListService;

    private static Logger log = LoggerFactory.getLogger(FileController.class);

    @PostMapping("upload")
    @ResponseBody
    public Object upload(MultipartFile file) throws IOException {
        log.info("上传文件");

        //以UUID作为文件夹名
        String uuid = "/" + String.valueOf(UUID.randomUUID());

        // 获得 classpath 的绝对路径
        String realPath = ResourceUtils.getURL("classpath:").getPath() + fileLocation + uuid ;
        log.info("realPath:" + realPath);
        File newFile = new File(realPath);
        // 如果文件夹不存在、则新建
        if (!newFile.exists()) newFile.mkdirs();
        // 上传
        String fileName =  file.getOriginalFilename();
        log.info("fileName：" + fileName);

        file.transferTo(new File(newFile, fileName));

        //将绝对路径存入数据库,为防止中文乱码，使用utf-8来编码
        String ecFilePath = java.net.URLEncoder.encode(realPath+"/"+fileName,"UTF-8");

        //返回使用utf-8来编码后的文件路径
        Map<String,String> pathMap = new ConcurrentHashMap<>();
        pathMap.put("ecFilePath",ecFilePath);

        log.info("ecFIlePath:" + ecFilePath);
        return JSON.toJSON(pathMap);
    }


    @GetMapping("download")
    public void download(String filePath, HttpServletResponse response) throws IOException {
        log.info("下载文件");
        //先解码
        String dcFilePath = java.net.URLDecoder.decode(filePath,"UTF-8");
        log.info("fileName：" + dcFilePath);

        try {

            //获取文件
            File file = new File(dcFilePath);

            if (!file.exists()){
                throw new Exception("文件不存在！");
            }

            // 获得文件输入流
            FileInputStream inputStream = new FileInputStream(file);

            // 设置响应头、以附件形式打开文件
            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载 inline表示在线打开 "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());

            ServletOutputStream outputStream = response.getOutputStream();
            int len = 0;
            byte[] data = new byte[1024];
            while ((len = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);
            }
            outputStream.close();
            inputStream.close();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
