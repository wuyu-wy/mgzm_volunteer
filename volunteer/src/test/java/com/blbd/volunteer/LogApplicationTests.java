package com.blbd.volunteer;

import com.blbd.volunteer.dao.LogEntityMapper;
import com.blbd.volunteer.dao.VolunteerEntityMapper;
import com.blbd.volunteer.dao.entity.LogEntity;
import com.blbd.volunteer.dao.entity.VolunteerEntity;
import com.blbd.volunteer.utils.UUIDUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = VolunteerApplication.class)
@RunWith(SpringRunner.class)
class LogApplicationTests {

    @Autowired
    private LogEntityMapper logEntityMapper;


    //新增log测试，测试成功
    @Test
    public void testInsertLog() {
        LogEntity logEntity = new LogEntity();
        logEntity.setLogChildId("吴昊");
        logEntity.setLogId("1111asd11");
        logEntity.setLogVolunteerId("周义勇");
        logEntity.setLogContent("好啊");
        logEntity.setLogDate(UUIDUtil.getCurrentTime());

        logEntityMapper.insert(logEntity);

    }

    @Test
    public void selectByVolunteerId() {

        List<LogEntity> logEntityList = new ArrayList<LogEntity>();

        LogEntity logEntity =new LogEntity();
        logEntity.setLogVolunteerId("周义勇");

        logEntityList = logEntityMapper.selectAll(logEntity);


        System.out.println(logEntityList);
    }


    }


