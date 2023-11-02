package com.blbd.volunteer.service.impl;

import com.blbd.volunteer.dao.entity.OrganizationEntity;
import com.blbd.volunteer.service.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    public int insertOrg(OrganizationEntity organizationEntity) {
        return 0;
    }

    public int updateByOrgId(OrganizationEntity organizationEntity) {
        return 0;
    }

    public List<OrganizationEntity> selectByVolunteerId(OrganizationEntity organizationEntity) {
        return null;
    }
}
