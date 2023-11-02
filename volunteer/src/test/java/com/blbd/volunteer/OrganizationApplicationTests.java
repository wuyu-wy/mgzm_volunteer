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

@SpringBootTest(classes = VolunteerApplication.class)
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


