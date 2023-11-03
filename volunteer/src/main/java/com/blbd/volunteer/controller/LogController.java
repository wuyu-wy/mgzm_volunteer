package com.blbd.volunteer.controller;

import com.blbd.volunteer.dao.entity.LogEntity;
import com.blbd.volunteer.service.LogService;
import com.blbd.volunteer.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/Log")
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping(value = "/insert", headers = "Accept=application/json")
    public int insertLog(LogEntity logEntity){
        return logService.insertLog(logEntity);
    }

    @PostMapping(value = "/update", headers = "Accept=application/json")
    public int updateLog(LogEntity logEntity){
        return logService.updateByLogId(logEntity);
    }

    @PostMapping(value = "/delete", headers = "Accept=application/json")
    public int deleteLog(LogEntity logEntity){
        return logService.deleteByLogId(logEntity);
    }

}
