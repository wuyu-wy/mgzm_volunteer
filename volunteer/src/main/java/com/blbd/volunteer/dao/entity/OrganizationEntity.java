package com.blbd.volunteer.dao.entity;

import lombok.Data;

@Data
public class OrganizationEntity {
    private String orgId;
    private String orgName;
    private String orgIntroduction;
    private int orgNumber;
    private String orgAddress;
    private String orgVolunteerId;
    private String orgPassIf;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgIntroduction() {
        return orgIntroduction;
    }

    public void setOrgIntroduction(String orgIntroduction) {
        this.orgIntroduction = orgIntroduction;
    }

    public int getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(int orgNumber) {
        this.orgNumber = orgNumber;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgVolunteerId() {
        return orgVolunteerId;
    }

    public void setOrgVolunteerId(String orgVolunteerId) {
        this.orgVolunteerId = orgVolunteerId;
    }

    public String getOrgPassIf() {
        return orgPassIf;
    }

    public void setOrgPassIf(String orgPassIf) {
        this.orgPassIf = orgPassIf;
    }

    @Override
    public String toString() {
        return "OrganizationEntity{" +
                "orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", orgIntroduction='" + orgIntroduction + '\'' +
                ", orgNumber=" + orgNumber +
                ", orgAddress='" + orgAddress + '\'' +
                ", orgVolunteerId='" + orgVolunteerId + '\'' +
                ", orgPassIf='" + orgPassIf + '\'' +
                '}';
    }
}