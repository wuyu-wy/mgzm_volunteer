package com.blbd.volunteer;

import com.blbd.volunteer.dao.OrganizationEntityMapper;
import com.blbd.volunteer.dao.VolunteerEntityMapper;
import com.blbd.volunteer.dao.entity.*;
import com.blbd.volunteer.service.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 测试类记得在springbootTest中加上webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
 * SpringBootTest在启动的时候不会启动服务器，所以WebSocket自然会报错，这个时候需要添加选项webEnvironment，以便提供一个测试的web环境。
 */
@SpringBootTest(classes = VolunteerApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class VolunteerApplicationTests {

    @Autowired
    private VolunteerEntityMapper volunteerEntityMapper;
    @Autowired
    private OrganizationEntityMapper organizationEntityMapper;


    //新增志愿者测试，测试成功
    @Test
    public void testInsertVolunteer() {
        VolunteerEntity volunteerEntity = new VolunteerEntity();
        volunteerEntity.setVolUsername("wuhao");
        volunteerEntity.setVolPassword("123456");
        volunteerEntity.setVolDuty(1);
        volunteerEntity.setVolId("12");

        List<VolunteerEntity> ifhave = volunteerEntityMapper.selectVolunteerUsername(volunteerEntity);

        if(ifhave.size() == 0) {

            int rowsInserted = volunteerEntityMapper.insert(volunteerEntity);

            assert rowsInserted == 1;
        }else{
            System.out.println("此用户名已存在");
        }

    }

    //查询志愿者信息，测试成功
    @Test
    public void testSelectVolunteer(){
        VolunteerEntity volunteerEntity = new VolunteerEntity();
        volunteerEntity.setVolUsername("wuhao");
        volunteerEntity.setVolPassword("123456");
        List<VolunteerEntity> rowsSelected = volunteerEntityMapper.selectVolunteerInfo(volunteerEntity);
        System.out.println(rowsSelected);

    }

    //修改志愿者信息测试
    @Test
    public void testUpdateVolunteer(){
        VolunteerEntity volunteerEntity = new VolunteerEntity();
        volunteerEntity.setVolUsername("mm2mm");
        volunteerEntity.setVolPassword("122234");

        int rowsSelected = volunteerEntityMapper.updateVolunteer(volunteerEntity);

        System.out.println(rowsSelected);
    }


    //志愿者加入组织测试
    @Test
    public void testJoin(){
        VolunteerEntity volunteerEntity = new VolunteerEntity();
        volunteerEntity.setVolUsername("wuwusss");
        volunteerEntity.setVolOrganization("2");

        volunteerEntityMapper.updateVolunteer(volunteerEntity);
        OrganizationEntity neworganizationEntity = new OrganizationEntity();
        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setOrgName(volunteerEntity.getVolOrganization());

        neworganizationEntity = organizationEntityMapper.selectNum(organizationEntity);
        organizationEntity.setOrgNumber(neworganizationEntity.getOrgNumber()+1);

        organizationEntityMapper.updateByOrgName(organizationEntity);



    }

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

    @Test
    public void selectTask() {

        VolunteerEntity volunteerEntity = new VolunteerEntity();
        volunteerEntity.setVolId("1");


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
            taskChildEntity.setChildId(task.getChildId());
            TaskChildEntity newTaskChildEntity = taskChildService.updatePhoto(taskChildEntity);

            task.setTaskName(newTaskEntity.getName());
            task.setTaskPhoto(newTaskEntity.getTaskPhoto());
            task.setChildName(newChildEntity.getName());
            task.setHomeworkPhoto(newTaskChildEntity.getHomeworkPhoto());
            task.setTaskVideo(newTaskEntity.getVideo());

            taskVolunteerService.updateNew(task);
        }

        System.out.println(volunteerService.selectTask(volunteerEntity));
    }

}


