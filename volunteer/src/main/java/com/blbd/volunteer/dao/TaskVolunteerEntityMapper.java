package com.blbd.volunteer.dao;


import com.blbd.volunteer.dao.entity.TaskVolunteerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface TaskVolunteerEntityMapper {
    //接受任务
    int insert(TaskVolunteerEntity taskVolunteerEntity);

    //修改任务信息
    int update(TaskVolunteerEntity taskVolunteerEntity);

    //查询任务信息
    List<TaskVolunteerEntity> selectAll(TaskVolunteerEntity taskVolunteerEntity);

}
