package com.blbd.volunteer.service;

import com.blbd.volunteer.dao.entity.LogEntity;

import java.util.List;

public interface LogService {

    //查找志愿者Id对应的日志
    List<LogEntity> selectByVolunteerId(LogEntity logEntity);

    //新增日志
    int insertLog(LogEntity logEntity);

    //删除日志
    int deleteByLogId(LogEntity logEntity);

    //修改日志
    int updateByLogId(LogEntity logEntity);
}
