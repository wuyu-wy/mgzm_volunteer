package com.blbd.volunteer.dao;


import com.blbd.volunteer.dao.entity.ChildEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface ChildEntityMapper {

    ChildEntity updateChildName(ChildEntity childEntity);

    ChildEntity searchScore(ChildEntity childEntity);

    int updateScore(ChildEntity childEntity);

}
