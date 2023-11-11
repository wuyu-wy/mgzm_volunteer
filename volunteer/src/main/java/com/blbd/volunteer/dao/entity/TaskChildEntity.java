package com.blbd.volunteer.dao.entity;

import lombok.Data;

@Data
public class TaskChildEntity {

    private String childId;
    private String taskId;
    private String homeworkPhoto;
    private byte correct;
    private int scores;
    private String comment;



}
