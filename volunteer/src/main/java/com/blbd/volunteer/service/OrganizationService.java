package com.blbd.volunteer.service;

import com.blbd.volunteer.dao.OrganizationEntityMapper;
import com.blbd.volunteer.dao.entity.OrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface OrganizationService {

    //新增组织
    int insertOrg(OrganizationEntity organizationEntity);

    //修改组织信息
    int updateByOrgId(OrganizationEntity organizationEntity);

    //查找组织列表
    List<OrganizationEntity> selectByVolunteerId(OrganizationEntity organizationEntity);

}
