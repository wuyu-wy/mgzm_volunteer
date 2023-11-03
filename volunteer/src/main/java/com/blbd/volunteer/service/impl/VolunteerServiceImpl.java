package com.blbd.volunteer.service.impl;

import com.blbd.volunteer.dao.VolunteerEntityMapper;
import com.blbd.volunteer.dao.entity.VolunteerEntity;
import com.blbd.volunteer.service.VolunteerService;
import com.blbd.volunteer.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private VolunteerEntityMapper volunteerEntityMapper;

    //志愿者登录
    @Override
    public List<VolunteerEntity> loginVolunteer(String username, String password) {
        VolunteerEntity volunteerEntity = new VolunteerEntity();
        volunteerEntity.setVolUsername(username);
        volunteerEntity.setVolPassword(password);

        List<VolunteerEntity> ifLogin = volunteerEntityMapper.selectVolunteerInfo(volunteerEntity);

        System.out.println(ifLogin);

       return ifLogin;
    }

    //志愿者注册
    @Override
    public int registerVolunteer(String username, String password) {

        VolunteerEntity volunteerEntity = new VolunteerEntity();
        volunteerEntity.setVolUsername(username);
        volunteerEntity.setVolPassword(password);
        volunteerEntity.setVolId(UUIDUtil.getOneUUID());

        List<VolunteerEntity> ifHave = volunteerEntityMapper.selectVolunteerUsername(volunteerEntity);

        if(ifHave.size() == 0) {
            volunteerEntityMapper.insert(volunteerEntity);
            return 1;
        }else{
            System.out.println("此用户名已存在");
            return 0;
        }
    }

    //志愿者信息查询
    @Override
    public List<VolunteerEntity> queryVolunteer(String username) {

        VolunteerEntity volunteerEntity = new VolunteerEntity();
        volunteerEntity.setVolUsername(username);

        List<VolunteerEntity> queryVolunteerInfo = volunteerEntityMapper.selectVolunteerInfo(volunteerEntity);

        System.out.println(queryVolunteerInfo);

        return queryVolunteerInfo;
    }

    //志愿者信息更新
    @Override
    public int updateVolunteer(VolunteerEntity volunteerEntity) {

        int ans = volunteerEntityMapper.updateVolunteer(volunteerEntity);

        return ans;
    }
}
