package com.blbd.volunteer.dao;


import com.blbd.volunteer.dao.entity.TaskChildEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface TaskChildEntityMapper {

    TaskChildEntity updatePhoto(TaskChildEntity taskChildEntity);

}
