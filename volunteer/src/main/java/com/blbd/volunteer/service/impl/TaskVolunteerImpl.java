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

    //新增任务-志愿者
    public int volunteerAcceptTask(TaskVolunteerEntity taskVolunteerEntity) {
        if(taskVolunteerEntityMapper.insert(taskVolunteerEntity) == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    //修改任务志愿者
    public int modifyTaskVolunteer(TaskVolunteerEntity taskVolunteerEntity) {
        if(taskVolunteerEntityMapper.update(taskVolunteerEntity) == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    //查询任务-志愿者
    public List<TaskVolunteerEntity> selectTaskVolunteer(TaskVolunteerEntity taskVolunteerEntity) {
        return taskVolunteerEntityMapper.selectAll(taskVolunteerEntity);
    }

}
