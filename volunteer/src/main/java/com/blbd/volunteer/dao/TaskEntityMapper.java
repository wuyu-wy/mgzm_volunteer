package com.blbd.volunteer.dao;


import com.blbd.volunteer.dao.entity.TaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface TaskEntityMapper {

    TaskEntity selectTaskInfo(TaskEntity taskEntity);

}
