package com.scientia.science.rcbscassignmentupload.Datasets;

public class NoticifationDataFinal {
    private String to;
    private NotificationDataOne data;

    public NoticifationDataFinal(String to, NotificationDataOne data) {
        this.to = to;
        this.data = data;
    }

    public NoticifationDataFinal() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public NotificationDataOne getData() {
        return data;
    }

    public void setData(NotificationDataOne data) {
        this.data = data;
    }
}
