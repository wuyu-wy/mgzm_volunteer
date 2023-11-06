package com.blbd.volunteer.service;

import com.blbd.volunteer.VolunteerApplication;
import com.blbd.volunteer.dao.VolunteerEntityMapper;
import com.blbd.volunteer.dao.entity.TaskVolunteerEntity;
import com.blbd.volunteer.dao.entity.VolunteerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface VolunteerService {


    //志愿者登录
    List<VolunteerEntity> loginVolunteer(String username, String password);

    //志愿者注册
    int registerVolunteer(VolunteerEntity volunteerEntity);

    //志愿者查询
    List<VolunteerEntity> queryVolunteer(String username);

    //志愿者修改
    int updateVolunteer(VolunteerEntity volunteerEntity);

    //志愿者加入组织
    int joinOrg(VolunteerEntity volunteerEntity);

    //志愿者退出组织
    int outOrg(VolunteerEntity volunteerEntity);

    //志愿者查询未完成任务
    List<TaskVolunteerEntity> selectTask(VolunteerEntity volunteerEntity);

    //志愿者模糊查询未完成任务
    List<TaskVolunteerEntity> searchTask(VolunteerEntity volunteerEntity);

    //志愿者查看某一详细任务
    TaskVolunteerEntity selectOneTask(TaskVolunteerEntity taskVolunteerEntity);

    //志愿者评价任务
    int evaluateTask(TaskVolunteerEntity taskVolunteerEntity);

    //志愿者模糊查询已完成任务
    List<TaskVolunteerEntity> finishSearchTask(VolunteerEntity volunteerEntity);


    //志愿者查询已完成任务
    List<TaskVolunteerEntity> finishTask(VolunteerEntity volunteerEntity);

//    //查询志愿者信息通过id
//    VolunteerEntity selectVolunteerById (VolunteerEntity volunteerEntity);

}
