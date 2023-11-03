package com.blbd.volunteer;

import com.blbd.volunteer.dao.VolunteerEntityMapper;
import com.blbd.volunteer.dao.entity.VolunteerEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest(classes = VolunteerApplication.class)
@RunWith(SpringRunner.class)
class VolunteerApplicationTests {

    @Autowired
    private VolunteerEntityMapper volunteerEntityMapper;


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

    }


