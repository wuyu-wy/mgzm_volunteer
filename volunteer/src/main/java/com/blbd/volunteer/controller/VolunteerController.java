package com.blbd.volunteer.controller;

import com.blbd.volunteer.dao.entity.VolunteerEntity;
import com.blbd.volunteer.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin("*")
@RestController
@RequestMapping("/Volunteer")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    //志愿者注册账号
    @PostMapping()
    public int registerVolunteer(@RequestBody String username, String password){

        //返回1注册成功，返回0注册失败
        return volunteerService.registerVolunteer(username,password);

    }

    //志愿者登录账号
    public List<VolunteerEntity> loginVolunteer(@RequestBody String username, String password){

        return volunteerService.loginVolunteer(username, password);
    }


    //志愿者显示信息
    public List<VolunteerEntity> queryVolunteer(@RequestBody String username){

        return volunteerService.queryVolunteer(username);
    }

    //志愿者信息修改
    public int updateVolunteer(@RequestBody VolunteerEntity volunteerEntity){

        return volunteerService.updateVolunteer(volunteerEntity);

    }




}
