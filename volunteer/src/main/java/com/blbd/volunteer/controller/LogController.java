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
    public int insertLog(@RequestParam LogEntity logEntity){
        return logService.insertLog(logEntity);
    }

    //修改日志
    @PostMapping(value = "/update", headers = "Accept=application/json")
    public int updateLog(@RequestParam LogEntity logEntity){
        return logService.updateByLogId(logEntity);
    }

    //删除日志
    @PostMapping(value = "/delete", headers = "Accept=application/json")
    public int deleteLog(@RequestParam LogEntity logEntity){return logService.deleteByLogId(logEntity);}


    //查找志愿者Id对应的日志
    @GetMapping(value = "selectByVolunteerId", headers = "Accept=application/json")
    public List<LogEntity> selectByVolunteerId(@RequestParam String id){return logService.selectByVolunteerId(id);}

    //分页查询
    @GetMapping(value = "selectByLimit" , headers = "Accept=application/json")
    public List<LogEntity>  selectByLimit(@RequestBody Integer curPage, Integer pageSize, String id){
        return logService.selectByLimit(curPage,  pageSize, id);
    }

}
