package com.blbd.volunteer.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TaskVolunteerEntity {
    private String volunteerId;
    private String childId;
    private String taskId;
    private Date approvalStartTime;
    private Date approvalFinishTime;
    private Date approvalEndTime;
    private byte isCompletedApproval;
    private String approvalComments;
    private String homeworkPhoto;
    private String taskPhoto;
    private String childName;
    private String taskName;
    private String taskVideo;
    private int getScore;
}