package com.scientia.science.rcbscassignmentupload.Datasets;

public class NoticeDataset {
    String title;
    String description;
    String cleass;
    String time;

    public NoticeDataset() {
    }

    public NoticeDataset(String title, String description, String cleass, String time) {
        this.title = title;
        this.description = description;
        this.cleass = cleass;
        this.time = time;
        }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCleass() {
        return cleass;
    }

    public void setCleass(String cleass) {
        this.cleass = cleass;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
