package com.scientia.science.rcbscassignmentupload.Datasets;

public class OnlineClassDataset {
    String title;
    String topic;
    String subject;
    String teacher;
    String meetinglink;
    String meetingid;
    String meetingpass;
    String time;

    public OnlineClassDataset(String title, String topic, String subject, String teacher, String meetinglink, String meetingid, String meetingpass, String time) {
        this.title = title;
        this.topic = topic;
        this.subject = subject;
        this.teacher = teacher;
        this.meetinglink = meetinglink;
        this.meetingid = meetingid;
        this.meetingpass = meetingpass;
        this.time = time;
    }

    public OnlineClassDataset() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getMeetinglink() {
        return meetinglink;
    }

    public void setMeetinglink(String meetinglink) {
        this.meetinglink = meetinglink;
    }

    public String getMeetingid() {
        return meetingid;
    }

    public void setMeetingid(String meetingid) {
        this.meetingid = meetingid;
    }

    public String getMeetingpass() {
        return meetingpass;
    }

    public void setMeetingpass(String meetingpass) {
        this.meetingpass = meetingpass;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
