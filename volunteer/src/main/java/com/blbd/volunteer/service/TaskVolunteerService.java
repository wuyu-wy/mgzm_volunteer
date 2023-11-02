package com.blbd.volunteer.service;

import com.blbd.volunteer.dao.entity.TaskVolunteerEntity;

import java.util.List;

public interface TaskVolunteerService {
    //志愿者接受任务
    int volunteerAcceptTask(TaskVolunteerEntity taskVolunteerEntity);
    //修改任务-志愿者
    int modifyTaskVolunteer(TaskVolunteerEntity taskVolunteerEntity);
    //查询任务-志愿者
    List<TaskVolunteerEntity> selectTaskVolunteer(TaskVolunteerEntity taskVolunteerEntity);
}
