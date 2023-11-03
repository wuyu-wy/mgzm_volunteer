package com.blbd.volunteer.service.impl;

import com.blbd.volunteer.dao.LogEntityMapper;
import com.blbd.volunteer.dao.entity.LogEntity;
import com.blbd.volunteer.service.LogService;
import com.blbd.volunteer.utils.UUIDUtil;
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

        logEntity.setLogId(UUIDUtil.getOneUUID());
        return logEntityMapper.insert(logEntity);

    }

    public int deleteByLogId(LogEntity logEntity) {
        return logEntityMapper.delete(logEntity);
    }

    public int updateByLogId(LogEntity logEntity) {
        return logEntityMapper.update(logEntity);
    }

    public List<LogEntity> selectByLimit(Integer curPage, Integer pageSize, LogEntity logEntity) {
        List<LogEntity> logEntityList = new ArrayList<LogEntity>();
        logEntityList = logEntityMapper.selectByLimit(curPage,pageSize,logEntity);
        return logEntityList;
    }
}
