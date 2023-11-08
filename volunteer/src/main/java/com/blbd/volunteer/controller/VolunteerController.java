package com.blbd.volunteer.controller;

import com.blbd.volunteer.dao.entity.*;
import com.blbd.volunteer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Volunteer")
@CrossOrigin("*")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private TaskVolunteerService taskVolunteerService;
    @Autowired
    private ChildService childService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskChildService taskChildService;


    // 志愿者注册账号
    @PostMapping(value = "/register", headers = "Accept=application/json")
    public int registerVolunteer(@RequestBody VolunteerEntity volunteerEntity){
        // 返回1注册成功，返回0注册失败（该用户名已被占用）
        return volunteerService.registerVolunteer(volunteerEntity);
    }


    //志愿者登录账号
    @GetMapping(value = "/login", headers = "Accept=application/json")
    public List<VolunteerEntity> loginVolunteer(@RequestParam("username") String username,
                                                @RequestParam("password") String password) {

        //根据给的账号密码，给出其list
        return volunteerService.loginVolunteer(username, password);
    }


    //志愿者显示信息
    @GetMapping(value = "queryVolunteer", headers = "Accept=application/json")
    public List<VolunteerEntity> queryVolunteer(@RequestParam("username") String username) {
        //根据给的用户名，提供对应list
        return volunteerService.queryVolunteer(username);
    }


    //志愿者信息更新
    @PostMapping(value = "/update", headers = "Accept=application/json")
    public int updateVolunteer(@RequestBody VolunteerEntity volunteerEntity) {
        //根据给出的新的list根据用户名更新
        //注：用户名不可修改
        return volunteerService.updateVolunteer(volunteerEntity);
    }

    //志愿者加入组织
    @PostMapping(value = "/joinOrg", headers = "Accept=application/json")
    public int joinOrg(@RequestBody VolunteerEntity volunteerEntity) {
        //增加vol_organization即可，其他的不动
        return volunteerService.joinOrg(volunteerEntity);
    }

    //志愿者退出组织
    @PostMapping(value = "/outOrg", headers = "Accept=application/json")
    public int outOrg(@RequestBody VolunteerEntity volunteerEntity) {
        //增加vol_organization即可，其他的不动
        return volunteerService.outOrg(volunteerEntity);
    }

    //志愿者查找所有未完成任务，点击任务按钮
    @PostMapping(value = "/selectTask", headers = "Accept=application/json")
    public List<TaskVolunteerEntity> selectTask(@RequestBody VolunteerEntity volunteerEntity) {

        List<TaskVolunteerEntity> tasks = volunteerService.selectTask(volunteerEntity);
        for (TaskVolunteerEntity task : tasks) {

            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setId(task.getTaskId());
            TaskEntity newTaskEntity = taskService.selectTaskInfo(taskEntity);

            ChildEntity childEntity = new ChildEntity();
            childEntity.setId(task.getChildId());
            ChildEntity newChildEntity = childService.updateChildName(childEntity);

            TaskChildEntity taskChildEntity = new TaskChildEntity();
            taskChildEntity.setTaskId(task.getTaskId());
            TaskChildEntity newTaskChildEntity = taskChildService.updatePhoto(taskChildEntity);

            task.setTaskName(newTaskEntity.getName());
            task.setTaskPhoto(newTaskEntity.getTaskPhoto());
            task.setChildName(newChildEntity.getName());
            task.setHomeworkPhoto(newTaskChildEntity.getHomeworkPhoto());

            taskVolunteerService.updateNew(task);
        }

        return volunteerService.selectTask(volunteerEntity);
    }

    //志愿者查看某一详细任务
    @PostMapping(value = "/selectOne", headers = "Accept=application/json")
    public TaskVolunteerEntity selectOne(@RequestBody TaskVolunteerEntity taskVolunteerEntity) {
        return volunteerService.selectOneTask(taskVolunteerEntity);
    }


    //志愿者评价并打分
    @PostMapping(value = "/evaluateTask", headers = "Accept=application/json")
    public int evaluateTask(@RequestBody TaskVolunteerEntity taskVolunteerEntity){
        return volunteerService.evaluateTask(taskVolunteerEntity);
    }

    //未完成任务模糊查询显示,根据taskName
    @PostMapping(value = "/searchTask", headers = "Accept=application/json")
    public List<TaskVolunteerEntity> searchTask(@RequestBody VolunteerEntity volunteerEntity){
        return volunteerService.searchTask(volunteerEntity);
    }


    //志愿者查询已完成任务
    @PostMapping(value = "/finishTask", headers = "Accept=application/json")
    public List<TaskVolunteerEntity> finishTask(@RequestBody VolunteerEntity volunteerEntity){
        return volunteerService.finishTask(volunteerEntity);
    }

    //已完成任务模糊查询显示,根据taskName
    @PostMapping(value = "/finishSearchTask", headers = "Accept=application/json")
    public List<TaskVolunteerEntity> finishSearchTask(@RequestBody VolunteerEntity volunteerEntity){
        return volunteerService.finishSearchTask(volunteerEntity);
    }









}
