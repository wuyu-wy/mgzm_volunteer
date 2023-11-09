package com.blbd.volunteer.controller;


import com.blbd.volunteer.dao.entity.OrganizationEntity;
import com.blbd.volunteer.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/Organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;



    //新增组织信息
    @PostMapping(value = "/insertOrg", headers = "Accept=application/json")
    public int registerVolunteer(@RequestBody OrganizationEntity organizationEntity) throws InterruptedException {
        return organizationService.insertOrg(organizationEntity);
    }


    //修改组织信息
    @PostMapping(value = "/updateByOrgId", headers = "Accept=application/json")
    public int updateByOrgId(@RequestBody OrganizationEntity organizationEntity)throws InterruptedException{
        return organizationService.updateByOrgId(organizationEntity);
    }


    //查询组织信息根据姓名，但不是模糊
    @PostMapping(value = "/selectByOrganizationName", headers = "Accept=application/json")
    public List<OrganizationEntity> selectByOrganizationName (@RequestBody OrganizationEntity organizationEntity){
        return organizationService.selectByOrganizationName(organizationEntity);
    }

    //查询组织信息根据姓名,模糊加分页
    @GetMapping(value = "selectByOrgName", headers = "Accept=application/json")
    public List<OrganizationEntity> selectByOrgName(@RequestParam Integer curPage,@RequestParam Integer pageSize,@RequestBody OrganizationEntity organizationEntity){
        return organizationService.selectByOrgName(curPage, pageSize,organizationEntity);
    }


}
