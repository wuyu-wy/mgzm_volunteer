package com.blbd.volunteer.service.impl;

import com.blbd.volunteer.dao.TaskVolunteerEntityMapper;
import com.blbd.volunteer.dao.entity.TaskVolunteerEntity;
import com.blbd.volunteer.service.TaskVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TaskVolunteerImpl implements TaskVolunteerService {
    @Autowired
    TaskVolunteerEntityMapper taskVolunteerEntityMapper;

    //更新信息
    public int updateNew(TaskVolunteerEntity taskVolunteerEntity){
        return taskVolunteerEntityMapper.updateNew(taskVolunteerEntity);
    }

}
