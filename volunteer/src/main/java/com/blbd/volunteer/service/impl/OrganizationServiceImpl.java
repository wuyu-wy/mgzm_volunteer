package com.blbd.volunteer.service.impl;

import com.blbd.volunteer.dao.OrganizationEntityMapper;
import com.blbd.volunteer.dao.entity.LogEntity;
import com.blbd.volunteer.dao.entity.OrganizationEntity;
import com.blbd.volunteer.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    public OrganizationEntityMapper organizationEntityMapper;

    public int insertOrg(OrganizationEntity organizationEntity) {
        if(organizationEntityMapper.insert(organizationEntity) == 1){
            return 1;
        }else{
            return 0;
        }
    }

    public int updateByOrgId(OrganizationEntity organizationEntity) {
        if(organizationEntityMapper.update(organizationEntity) == 1){
            return 1;
        }else{
            return 0;
        }
    }

    public List<OrganizationEntity> selectByVolunteerId(OrganizationEntity organizationEntity) {
        List<OrganizationEntity> organizationEntityList = new ArrayList<OrganizationEntity>();
        organizationEntityList = organizationEntityMapper.selectByOrganizationName(organizationEntity);
        return organizationEntityList;
    }
}
