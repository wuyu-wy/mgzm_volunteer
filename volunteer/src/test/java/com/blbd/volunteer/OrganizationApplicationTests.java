package com.blbd.volunteer;

import com.blbd.volunteer.dao.OrganizationEntityMapper;
import com.blbd.volunteer.dao.VolunteerEntityMapper;
import com.blbd.volunteer.dao.entity.OrganizationEntity;
import com.blbd.volunteer.dao.entity.VolunteerEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
/**
 * 测试类记得在springbootTest中加上webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
 * SpringBootTest在启动的时候不会启动服务器，所以WebSocket自然会报错，这个时候需要添加选项webEnvironment，以便提供一个测试的web环境。
 */
@SpringBootTest(classes = VolunteerApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class OrganizationApplicationTests {
    @Autowired
    public OrganizationEntityMapper organizationEntityMapper;

    @Test
    public void testUpdateOrg() {
        OrganizationEntity org = new OrganizationEntity();
        org.setOrgId("1");
        org.setOrgName("2");
        int rowsInserted = organizationEntityMapper.update(org);
        System.out.println(rowsInserted);
        assert rowsInserted == 1;
    }

    @Test
    public void testSelectOrg(){
        OrganizationEntity org = new OrganizationEntity();
        org.setOrgId("1");
        org.setOrgName("2");
        List<OrganizationEntity> row = organizationEntityMapper.selectByOrganizationName(org);

        System.out.println(row);
    }
    }


