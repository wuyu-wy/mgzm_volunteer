package com.blbd.volunteer.dao;


import com.blbd.volunteer.dao.entity.LogEntity;
import com.blbd.volunteer.dao.entity.OrganizationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface LogEntityMapper {
    //新增日式
    int insert(LogEntity logEntity);

    //删除日式
    int delete(LogEntity logEntity);

    //查找所有
    List<LogEntity> selectAll(LogEntity logEntity);

    //修改日志
    int update(LogEntity logEntity);

    //分页查询
    List<LogEntity> selectByLimit(Integer curPage,Integer pageSize,LogEntity logEntity);

}

