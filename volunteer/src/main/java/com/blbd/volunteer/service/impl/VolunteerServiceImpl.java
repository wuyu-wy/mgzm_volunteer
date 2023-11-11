package com.blbd.volunteer.service.impl;

import com.blbd.volunteer.dao.*;
import com.blbd.volunteer.dao.entity.*;
import com.blbd.volunteer.service.TaskChildService;
import com.blbd.volunteer.service.VolunteerService;
import com.blbd.volunteer.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private VolunteerEntityMapper volunteerEntityMapper;
    @Autowired
    private OrganizationEntityMapper organizationEntityMapper;

    @Autowired
    private TaskVolunteerEntityMapper taskVolunteerEntityMapper;
    @Autowired
    private TaskChildEntityMapper taskChildEntityMapper;

    @Autowired
    private ChildEntityMapper childEntityMapper;

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
    public int registerVolunteer(VolunteerEntity volunteerEntity) {

        volunteerEntity.setIfPass("0");
        volunteerEntity.setVolDuty(0);
        volunteerEntity.setVolCorrectedTasks(0);
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
    public List<VolunteerEntity> queryVolunteer(String id) {

        VolunteerEntity volunteerEntity = new VolunteerEntity();
        volunteerEntity.setVolUsername(id);

        List<VolunteerEntity> queryVolunteerInfo = volunteerEntityMapper.selectVolunteerInfo(volunteerEntity);

        System.out.println(queryVolunteerInfo);

        return queryVolunteerInfo;
    }

    //志愿者信息更新
    @Override
    public int updateVolunteer(VolunteerEntity volunteerEntity) {
        return  volunteerEntityMapper.updateVolunteer(volunteerEntity);
    }


    //志愿者加入组织
    @Override
    public int joinOrg(String volId , String OrgName) {
        VolunteerEntity aa = new VolunteerEntity();
        aa.setVolId(volId);
        VolunteerEntity volunteerEntity = volunteerEntityMapper.selectVolunteerById(aa);
        if(volunteerEntity.getVolOrganization()!= null){
            return 0;
        }
        volunteerEntity.setVolOrganization(OrgName);


        volunteerEntityMapper.updateVolunteer(volunteerEntity);

        OrganizationEntity neworganizationEntity = new OrganizationEntity();

        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setOrgName(volunteerEntity.getVolOrganization());

        neworganizationEntity = organizationEntityMapper.selectNum(organizationEntity);
        organizationEntity.setOrgNumber(neworganizationEntity.getOrgNumber()+1);

        organizationEntityMapper.updateByOrgName(organizationEntity);
        return volunteerEntityMapper.updateVolunteer(volunteerEntity);
    }

    //志愿者退出组织
    @Override
    public int outOrg(String volId) {

        VolunteerEntity aa = new VolunteerEntity();
        aa.setVolId(volId);
        VolunteerEntity volunteerEntity = volunteerEntityMapper.selectVolunteerById(aa);

        volunteerEntity.setVolOrganization(null);
        volunteerEntityMapper.updateVolunteer(volunteerEntity);

        OrganizationEntity neworganizationEntity = new OrganizationEntity();
        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setOrgName(volunteerEntity.getVolOrganization());
        neworganizationEntity = organizationEntityMapper.selectNum(organizationEntity);
        organizationEntity.setOrgNumber(neworganizationEntity.getOrgNumber()-1);
        organizationEntityMapper.updateByOrgName(organizationEntity);
        return volunteerEntityMapper.updateVolunteer(volunteerEntity);
    }

    //志愿者查看自己的所有未完成任务
    @Override
    public List<TaskVolunteerEntity> selectTask(VolunteerEntity volunteerEntity){

        TaskVolunteerEntity taskvolunteerEntity = new TaskVolunteerEntity();
        taskvolunteerEntity.setVolunteerId(volunteerEntity.getVolId());
        return taskVolunteerEntityMapper.selectAll(taskvolunteerEntity);

    }

    //志愿者模糊查询未完成任务
    @Override
    public List<TaskVolunteerEntity> searchTask(VolunteerEntity volunteerEntity, String taskName) {

        TaskVolunteerEntity taskvolunteerEntity = new TaskVolunteerEntity();
        taskvolunteerEntity.setVolunteerId(volunteerEntity.getVolId());
        taskvolunteerEntity.setTaskName(taskName);
        return taskVolunteerEntityMapper.search(taskvolunteerEntity);
    }


        //志愿者查看某一任务细节
    @Override
    public TaskVolunteerEntity selectOneTask(TaskVolunteerEntity taskVolunteerEntity){
        return taskVolunteerEntityMapper.selectOne(taskVolunteerEntity);
    }


    //志愿者评价任务
    @Override
    public int evaluateTask(TaskVolunteerEntity taskVolunteerEntity){

        //查询此志愿者信息,duty-1 ,CorrectedTasks+1
        VolunteerEntity volunteerEntity = new VolunteerEntity();
        volunteerEntity.setVolId(taskVolunteerEntity.getVolunteerId());
        VolunteerEntity newvolunteerEntity = volunteerEntityMapper.selectVolunteerById(volunteerEntity);
        newvolunteerEntity.setVolDuty(newvolunteerEntity.getVolDuty() -1);
        newvolunteerEntity.setVolCorrectedTasks(newvolunteerEntity.getVolCorrectedTasks() + 1);
        volunteerEntityMapper.updateVolunteer(newvolunteerEntity);
        taskVolunteerEntity.setApprovalFinishTime(UUIDUtil.getCurrentTime());

        TaskChildEntity taskChildEntity = new TaskChildEntity();
        taskChildEntity.setChildId(taskVolunteerEntity.getChildId());
        taskChildEntity.setTaskId(taskVolunteerEntity.getTaskId());
        taskChildEntity.setCorrect(taskVolunteerEntity.getIsCompletedApproval());
        taskChildEntity.setComment(taskVolunteerEntity.getApprovalComments());

        taskChildEntityMapper.correct(taskChildEntity);

        TaskChildEntity aa = new TaskChildEntity();

        aa = taskChildEntityMapper.updatePhoto(taskChildEntity);
        int score = aa.getScores();
        System.out.println("sswhhhhh"+  score);


        ChildEntity ss = new ChildEntity();
        ss.setId(taskVolunteerEntity.getChildId());
        ChildEntity ans = childEntityMapper.searchScore(ss);
        System.out.println("sssswww222" + ans.getScore());

        ChildEntity childEntity = new ChildEntity();
        childEntity.setId(taskVolunteerEntity.getChildId());
        childEntity.setScore(ans.getScore() + score);

        childEntityMapper.updateScore(childEntity);

        return taskVolunteerEntityMapper.evaluateTask(taskVolunteerEntity);
    }

    //志愿者查询已完成的信息
    @Override
    public List<TaskVolunteerEntity> finishTask(VolunteerEntity volunteerEntity){
        TaskVolunteerEntity taskvolunteerEntity = new TaskVolunteerEntity();
        taskvolunteerEntity.setVolunteerId(volunteerEntity.getVolId());
        return taskVolunteerEntityMapper.finishTask(taskvolunteerEntity);
    }

    @Override
    //志愿者查询已完成的信息，模糊搜索
    public List<TaskVolunteerEntity> finishSearchTask(VolunteerEntity volunteerEntity ,String taskName) {

        TaskVolunteerEntity taskvolunteerEntity = new TaskVolunteerEntity();
        taskvolunteerEntity.setVolunteerId(volunteerEntity.getVolId());
        taskvolunteerEntity.setTaskName(taskName);
        return taskVolunteerEntityMapper.finishSearchTask(taskvolunteerEntity);
    }




}
