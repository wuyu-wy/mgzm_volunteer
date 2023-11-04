package com.blbd.volunteer.dao;


import com.blbd.volunteer.dao.entity.LogEntity;
import com.blbd.volunteer.dao.entity.OrganizationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface OrganizationEntityMapper {

    //新增组织
    int insert(OrganizationEntity organizationEntity);

    //修改组织信息
    int update(OrganizationEntity organizationEntity);

    int updateByOrgName(OrganizationEntity organizationEntity);

    //组织列表
    List<OrganizationEntity> selectByOrganizationName(OrganizationEntity organizationEntity);
    //分页查询
    List<OrganizationEntity> selectByLimit(Integer curPage,Integer pageSize,OrganizationEntity organizationEntity);
}
