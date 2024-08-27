package com.scientia.science.rcbscassignmentupload.Datasets;

public class ContactDataset {
    String name;
    String phonenum;

    public ContactDataset(String name, String phonenum) {
        this.name = name;
        this.phonenum = phonenum;
    }

    public ContactDataset() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }
}
