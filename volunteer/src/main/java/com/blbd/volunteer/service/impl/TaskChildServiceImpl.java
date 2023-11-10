package com.blbd.volunteer.service.impl;

import com.blbd.volunteer.dao.ChildEntityMapper;
import com.blbd.volunteer.dao.TaskChildEntityMapper;
import com.blbd.volunteer.dao.entity.TaskChildEntity;
import com.blbd.volunteer.service.TaskChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskChildServiceImpl implements TaskChildService {


    @Autowired
    private TaskChildEntityMapper taskChildEntityMapper;


    public TaskChildEntity updatePhoto(TaskChildEntity taskChildEntity){
        return taskChildEntityMapper.updatePhoto(taskChildEntity);
    }

    public int correct(TaskChildEntity taskChildEntity){
        return taskChildEntityMapper.correct(taskChildEntity);
    }
}
