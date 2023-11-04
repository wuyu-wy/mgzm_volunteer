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
    List<OrganizationEntity> selectByOrganizationName(OrganizationEntity organizationEntity);

    //查询组织信息根据姓名,模糊加分页
    List<OrganizationEntity> selectByOrgName(Integer curPage, Integer pageSize, OrganizationEntity organizationEntity);

    int updateByOrgName(OrganizationEntity organizationEntity);
}
