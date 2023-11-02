package com.blbd.volunteer.service.impl;

import com.blbd.volunteer.dao.entity.VolunteerEntity;
import com.blbd.volunteer.service.VolunteerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    //志愿者登录
    @Override
    public int loginVolunteer(VolunteerEntity volunteerEntity) {



        return 1;
    }

    //志愿者注册
    @Override
    public int registerVolunteer(VolunteerEntity volunteerEntity) {



        return 0;
    }

    //志愿者
    @Override
    public List<VolunteerEntity> queryVolunteer(VolunteerEntity volunteerentity) {
        return null;
    }

    @Override
    public int updateVolunteer(VolunteerEntity volunteerEntity) {

        return 0;
    }
}
