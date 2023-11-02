package com.blbd.volunteer.service.impl;

import com.blbd.volunteer.dao.VolunteerEntityMapper;
import com.blbd.volunteer.dao.entity.VolunteerEntity;
import com.blbd.volunteer.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private VolunteerEntityMapper volunteerEntityMapper;

    //志愿者登录
    @Override
    public List<VolunteerEntity> loginVolunteer(VolunteerEntity volunteerEntity) {

        List<VolunteerEntity> ifLogin = volunteerEntityMapper.selectVolunteerInfo(volunteerEntity);

        System.out.println(ifLogin);

       return ifLogin;
    }

    //志愿者注册
    @Override
    public int registerVolunteer(VolunteerEntity volunteerEntity) {
        List<VolunteerEntity> ifHave = volunteerEntityMapper.selectVolunteerUsername(volunteerEntity);

        if(ifHave.size() == 0) {
            volunteerEntityMapper.insert(volunteerEntity);
        }else{
            System.out.println("此用户名已存在");
        }

        return ifHave.size();

    }

    //志愿者信息查询
    @Override
    public List<VolunteerEntity> queryVolunteer(VolunteerEntity volunteerEntity) {

        List<VolunteerEntity> queryVolunteer = volunteerEntityMapper.selectVolunteerInfo(volunteerEntity);

        System.out.println(queryVolunteer);

        return queryVolunteer;
    }

    //志愿者信息更新
    @Override
    public int updateVolunteer(VolunteerEntity volunteerEntity) {

        int ans = volunteerEntityMapper.updateVolunteer(volunteerEntity);
        return ans;
    }
}
