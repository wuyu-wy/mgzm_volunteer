package com.blbd.volunteer;

import com.blbd.volunteer.dao.TaskVolunteerEntityMapper;
import com.blbd.volunteer.dao.VolunteerEntityMapper;
import com.blbd.volunteer.dao.entity.OrganizationEntity;
import com.blbd.volunteer.dao.entity.TaskVolunteerEntity;
import com.blbd.volunteer.dao.entity.VolunteerEntity;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试类记得在springbootTest中加上webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
 * SpringBootTest在启动的时候不会启动服务器，所以WebSocket自然会报错，这个时候需要添加选项webEnvironment，以便提供一个测试的web环境。
 */
@SpringBootTest(classes = VolunteerApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class TaskVolunteerApplicationTests {
    @Autowired
    TaskVolunteerEntityMapper taskVolunteerEntityMapper;

    @Test
    public void testInsert() {
        TaskVolunteerEntity taskVolunteerEntity = new TaskVolunteerEntity();
        taskVolunteerEntity.setVolunteerId("1");
        taskVolunteerEntity.setTaskId("1");
        taskVolunteerEntity.setChildId("1");
        taskVolunteerEntity.setApprovalStartTime(new Date(System.currentTimeMillis()));
        taskVolunteerEntity.setIsCompletedApproval((byte) 0);
        int res = taskVolunteerEntityMapper.insert(taskVolunteerEntity);
        assert res == 1;
    }



    @Test
    public void testSelectAll() {
        List<TaskVolunteerEntity> list = new ArrayList<TaskVolunteerEntity>();
        TaskVolunteerEntity taskVolunteerEntity = new TaskVolunteerEntity();
        taskVolunteerEntity.setVolunteerId("1");
        taskVolunteerEntity.setChildId("1");
        list = taskVolunteerEntityMapper.selectAll(taskVolunteerEntity);
        assert list.size() != 0;
    }



    }


