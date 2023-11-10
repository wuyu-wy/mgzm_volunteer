package com.blbd.volunteer.controller;

import com.blbd.volunteer.dao.entity.LogEntity;
import com.blbd.volunteer.service.LogService;
import com.blbd.volunteer.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/Log")
public class LogController {

    @Autowired
    private LogService logService;


    //新增日志
    @PostMapping(value = "/insert", headers = "Accept=application/json")
    public int insertLog(@RequestBody LogEntity logEntity){
        return logService.insertLog(logEntity);
    }

    //修改日志
    @PostMapping(value = "/update", headers = "Accept=application/json")
    public int updateLog(@RequestBody LogEntity logEntity){
        return logService.updateByLogId(logEntity);
    }

    //删除日志
    @PostMapping(value = "/delete", headers = "Accept=application/json")
    public int deleteLog(@RequestBody String logId){
        LogEntity logEntity = new LogEntity();
        logEntity.setLogId(logId);
        return logService.deleteByLogId(logEntity);}

    //查找志愿者Id对应的日志
    @GetMapping(value = "selectByVolunteerId", headers = "Accept=application/json")
    public List<LogEntity> selectByVolunteerId(@RequestParam String id){return logService.selectByVolunteerId(id);}

    //分页查询
    @GetMapping(value = "selectByLimit" , headers = "Accept=application/json")
    public List<LogEntity>  selectByLimit(@RequestBody Integer curPage, @RequestParam Integer pageSize, @RequestBody String id){
        return logService.selectByLimit(curPage,  pageSize, id);
    }

}
