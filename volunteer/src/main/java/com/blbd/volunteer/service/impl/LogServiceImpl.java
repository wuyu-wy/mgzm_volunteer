package com.blbd.volunteer.service.impl;

import com.blbd.volunteer.dao.LogEntityMapper;
import com.blbd.volunteer.dao.entity.LogEntity;
import com.blbd.volunteer.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogServiceImpl implements LogService{

    @Autowired
    public LogEntityMapper logEntityMapper;

    public List<LogEntity> selectByVolunteerId(LogEntity logEntity) {
        List<LogEntity> logEntityList = new ArrayList<LogEntity>();
        logEntityList = logEntityMapper.selectAll(logEntity);
        return logEntityList;
    }

    public int insertLog(LogEntity logEntity) {
        if(logEntityMapper.insert(logEntity) == 1){
            return 1;
        }else{
            return 0;
        }
    }

    public int deleteByLogId(LogEntity logEntity) {
        if(logEntityMapper.delete(logEntity) == 1){
            return 1;
        }else{
            return 0;
        }
    }

    public int updateByLogId(LogEntity logEntity) {
        if(logEntityMapper.update(logEntity) == 1){
            return 1;
        }else{
            return 0;
        }
    }

    public List<LogEntity> selectByLimit(Integer curPage, Integer pageSize, LogEntity logEntity) {
        List<LogEntity> logEntityList = new ArrayList<LogEntity>();
        logEntityList = logEntityMapper.selectByLimit(curPage,pageSize,logEntity);
        return logEntityList;
    }
}
