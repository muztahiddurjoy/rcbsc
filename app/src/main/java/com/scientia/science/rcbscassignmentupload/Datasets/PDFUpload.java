package com.scientia.science.rcbscassignmentupload.Datasets;

public class PDFUpload {
    String title;
    String name;
    String section;
    String roll;
    String submitted_to;
    String subject;
    String cleass;
    String url;
    String time;

    public PDFUpload(String title, String name, String section, String roll, String submitted_to, String subject, String cleass, String url, String time) {
        this.title = title;
        this.name = name;
        this.section = section;
        this.roll = roll;
        this.submitted_to = submitted_to;
        this.subject = subject;
        this.cleass = cleass;
        this.url = url;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getSubmitted_to() {
        return submitted_to;
    }

    public void setSubmitted_to(String submitted_to) {
        this.submitted_to = submitted_to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCleass() {
        return cleass;
    }

    public void setCleass(String cleass) {
        this.cleass = cleass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public PDFUpload() {
    }


}
