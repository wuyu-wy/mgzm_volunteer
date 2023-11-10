package com.blbd.volunteer.service;

import com.blbd.volunteer.dao.entity.TaskChildEntity;

public interface TaskChildService {

    TaskChildEntity updatePhoto(TaskChildEntity taskChildEntity);

    int correct(TaskChildEntity taskChildEntity);
}
