package com.blbd.volunteer.service.impl;

import com.blbd.volunteer.dao.TaskEntityMapper;
import com.blbd.volunteer.dao.entity.TaskEntity;
import com.blbd.volunteer.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskEntityMapper taskEntityMapper;

    public TaskEntity selectTaskInfo(TaskEntity taskEntity){

        return taskEntityMapper.selectTaskInfo(taskEntity);
    }
}
