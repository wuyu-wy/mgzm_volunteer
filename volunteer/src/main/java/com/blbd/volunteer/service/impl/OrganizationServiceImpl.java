package com.blbd.volunteer.service.impl;

import com.blbd.volunteer.dao.OrganizationEntityMapper;
import com.blbd.volunteer.dao.entity.LogEntity;
import com.blbd.volunteer.dao.entity.OrganizationEntity;
import com.blbd.volunteer.service.OrganizationService;
import com.blbd.volunteer.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    public OrganizationEntityMapper organizationEntityMapper;


    //新增组织
    public int insertOrg(OrganizationEntity organizationEntity) {
        organizationEntity.setOrgId(UUIDUtil.getOneUUID());
        organizationEntity.setOrgNumber(1);
        organizationEntity.setOrgPassIf("no");

        List<OrganizationEntity> ifHave = organizationEntityMapper.selectByOrganizationName(organizationEntity);

        if(ifHave.size() == 0) {
            organizationEntityMapper.insert(organizationEntity);
            return 1;
        }else{
            System.out.println("此组织已存在");
            return 0;
        }
    }

    //修改组织信息
    public int updateByOrgId(OrganizationEntity organizationEntity) {
       return organizationEntityMapper.update(organizationEntity);
    }



    //查找组织列表
    public List<OrganizationEntity> selectByOrganizationName(OrganizationEntity organizationEntity) {
        List<OrganizationEntity> organizationEntityList = new ArrayList<OrganizationEntity>();
        organizationEntityList = organizationEntityMapper.selectByOrganizationName(organizationEntity);
        return organizationEntityList;
    }


    //查询组织信息根据姓名,模糊加分页
    public List<OrganizationEntity> selectByOrgName(Integer curPage, Integer pageSize,OrganizationEntity organizationEntity){
        List<OrganizationEntity> organizationEntityList = new ArrayList<OrganizationEntity>();
        organizationEntityList = organizationEntityMapper.selectByOrganizationName(organizationEntity);
        return organizationEntityList;
    }


    //通过姓名查询为更新numver
    public int updateByOrgName(OrganizationEntity organizationEntity) {
        return organizationEntityMapper.updateByOrgName(organizationEntity);
    }
}
