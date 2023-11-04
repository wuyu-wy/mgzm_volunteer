package com.blbd.volunteer;

import com.blbd.volunteer.dao.LogEntityMapper;
import com.blbd.volunteer.dao.VolunteerEntityMapper;
import com.blbd.volunteer.dao.entity.LogEntity;
import com.blbd.volunteer.dao.entity.VolunteerEntity;
import com.blbd.volunteer.utils.UUIDUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试类记得在springbootTest中加上webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
 * SpringBootTest在启动的时候不会启动服务器，所以WebSocket自然会报错，这个时候需要添加选项webEnvironment，以便提供一个测试的web环境。
 */
@SpringBootTest(classes = VolunteerApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = VolunteerApplication.class)
@RunWith(SpringRunner.class)
class LogApplicationTests {

    @Autowired
    private LogEntityMapper logEntityMapper;


    //新增log测试，测试成功
    @Test
    public void testInsertLog() {
        LogEntity logEntity = new LogEntity();
        logEntity.setLogChildId("吴昊");
        logEntity.setLogId("1111asd11");
        logEntity.setLogVolunteerId("周义勇");
        logEntity.setLogContent("好啊");
        logEntity.setLogDate(UUIDUtil.getCurrentTime());

        logEntityMapper.insert(logEntity);

    }

    @Test
    public void selectByVolunteerId() {

        List<LogEntity> logEntityList = new ArrayList<LogEntity>();

        LogEntity logEntity =new LogEntity();
        logEntity.setLogVolunteerId("周义勇");

        logEntityList = logEntityMapper.selectAll(logEntity);


        System.out.println(logEntityList);
    }


    }


