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
    List<VolunteerEntity> loginVolunteer(String username, String password);

    //志愿者注册
    int registerVolunteer(String username,String password);

    //志愿者查询
    List<VolunteerEntity> queryVolunteer(String username);

    //志愿者修改
    int updateVolunteer(VolunteerEntity volunteerEntity);

    //志愿者加入组织
    int joinOrg(VolunteerEntity volunteerEntity);


}
