package com.scientia.science.rcbscassignmentupload.Datasets;

public class AssignmentDataset {
    String title;
    String submitdate;
    String uploaddate;
    String cleass;
    String subject;

    public AssignmentDataset(String title, String submitdate, String uploaddate, String cleass, String subject) {
        this.title = title;
        this.submitdate = submitdate;
        this.uploaddate = uploaddate;
        this.cleass = cleass;
        this.subject = subject;
    }

    public AssignmentDataset() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(String submitdate) {
        this.submitdate = submitdate;
    }

    public String getUploaddate() {
        return uploaddate;
    }

    public void setUploaddate(String uploaddate) {
        this.uploaddate = uploaddate;
    }

    public String getCleass() {
        return cleass;
    }

    public void setCleass(String cleass) {
        this.cleass = cleass;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
