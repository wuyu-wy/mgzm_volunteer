package com.blbd.volunteer.service.impl;

import com.blbd.volunteer.dao.ChildEntityMapper;
import com.blbd.volunteer.dao.TaskEntityMapper;
import com.blbd.volunteer.dao.entity.ChildEntity;
import com.blbd.volunteer.dao.entity.TaskEntity;
import com.blbd.volunteer.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChildServiceImpl implements ChildService {


    @Autowired
    private ChildEntityMapper childEntityMapper;

    public ChildEntity updateChildName(ChildEntity childEntity){

        return childEntityMapper.updateChildName(childEntity);
    }
}
