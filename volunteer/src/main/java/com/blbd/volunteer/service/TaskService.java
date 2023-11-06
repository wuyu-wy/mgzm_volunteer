package com.blbd.volunteer.service;

import com.blbd.volunteer.dao.entity.TaskEntity;

public interface TaskService {


    TaskEntity selectTaskInfo(TaskEntity taskEntity);
}
