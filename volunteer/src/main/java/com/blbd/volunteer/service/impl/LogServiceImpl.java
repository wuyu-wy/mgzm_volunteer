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


    //查找志愿者Id对应的日志
    public List<LogEntity> selectByVolunteerId(String id) {

        List<LogEntity> logEntityList = new ArrayList<LogEntity>();
        LogEntity logEntity =new LogEntity();
        logEntity.setLogId(id);
        logEntityList = logEntityMapper.selectAll(logEntity);
        return logEntityList;
    }

    //新增日志
    public int insertLog(LogEntity logEntity) {

        logEntity.setLogId(UUIDUtil.getOneUUID());
        return logEntityMapper.insert(logEntity);

    }


    //删除日志
    public int deleteByLogId(LogEntity logEntity) {
        return logEntityMapper.delete(logEntity);
    }


    //修改日志
    public int updateByLogId(LogEntity logEntity) {
        return logEntityMapper.update(logEntity);
    }


    //分页查询
    public List<LogEntity> selectByLimit(Integer curPage, Integer pageSize, String id) {
        List<LogEntity> logEntityList = new ArrayList<LogEntity>();
        LogEntity logEntity = new LogEntity();
        logEntity.setLogVolunteerId(id);
        logEntityList = logEntityMapper.selectByLimit(curPage,pageSize,logEntity);
        return logEntityList;
    }
}
