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


}
