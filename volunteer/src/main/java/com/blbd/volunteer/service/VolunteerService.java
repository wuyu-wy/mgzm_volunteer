package com.blbd.volunteer.service;

import com.blbd.volunteer.VolunteerApplication;
import com.blbd.volunteer.dao.VolunteerEntityMapper;
import com.blbd.volunteer.dao.entity.VolunteerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface VolunteerService {


    //志愿者登录
    int loginVolunteer(VolunteerEntity volunteerEntity);

    //志愿者注册
    int registerVolunteer(VolunteerEntity volunteerEntity);

    //志愿者查询
    List<VolunteerEntity> queryVolunteer(VolunteerEntity volunteerentity);

    //志愿者修改
    int updateVolunteer(VolunteerEntity volunteerEntity);


}
