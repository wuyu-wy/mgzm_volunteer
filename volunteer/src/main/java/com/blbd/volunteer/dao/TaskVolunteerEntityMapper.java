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

    //更新信息
    int updateNew(TaskVolunteerEntity taskVolunteerEntity);

    //更新志愿者任务信息
    int updateInfo(TaskVolunteerEntity taskVolunteerEntity);

    //志愿者评价任务
    int evaluateTask(TaskVolunteerEntity taskVolunteerEntity);

    //查询所有与志愿者未完成任务信息
    List<TaskVolunteerEntity> selectAll(TaskVolunteerEntity taskVolunteerEntity);

    //查询所有与未完成志愿者任务信息，模糊查询,根据taskName
    List<TaskVolunteerEntity> search(TaskVolunteerEntity taskVolunteerEntity);

    //查询所有已完成的任务信息
    List<TaskVolunteerEntity> finishTask(TaskVolunteerEntity taskVolunteerEntity);


    //查询所有与已完成志愿者任务信息，模糊查询,根据taskName
    List<TaskVolunteerEntity> finishSearchTask(TaskVolunteerEntity taskVolunteerEntity);

    //根据任务ID查询单一任务
    TaskVolunteerEntity selectOne(TaskVolunteerEntity taskVolunteerEntity);

}
