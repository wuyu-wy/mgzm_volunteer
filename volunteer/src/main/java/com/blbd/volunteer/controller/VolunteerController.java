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


    // 志愿者注册账号
    @PostMapping(value = "/register", headers = "Accept=application/json")
    public int registerVolunteer(@RequestParam String username,  String password) throws InterruptedException {
        // 返回1注册成功，返回0注册失败（该用户名已被占用）
        return volunteerService.registerVolunteer(username, password);
    }



    //志愿者登录账号
    @PostMapping(value = "/login", headers = "Accept=application/json")
    public List<VolunteerEntity> loginVolunteer(@RequestParam String username, String password){

        //根据给的账号密码，给出其list
        return volunteerService.loginVolunteer(username, password);
    }


    //志愿者显示信息
    @GetMapping(value = "queryVolunteer")
    public List<VolunteerEntity> queryVolunteer(@RequestBody String username){
        //根据给的用户名，提供对应list
        return volunteerService.queryVolunteer(username);
    }



    @PostMapping(value = "/update", headers = "Accept=application/json")
    public int updateVolunteer(VolunteerEntity volunteerEntity){

        //根据给出的新的list根据用户名更新
        //注：用户名不可修改
        return volunteerService.updateVolunteer(volunteerEntity);

    }




}
