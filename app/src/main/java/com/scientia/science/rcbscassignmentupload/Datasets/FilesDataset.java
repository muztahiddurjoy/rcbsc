package com.scientia.science.rcbscassignmentupload.Datasets;

public class FilesDataset {
    String filename;
    String uploader;
    String datetime;
    String url;

    public FilesDataset() {
    }

    public FilesDataset(String filename, String uploader, String datetime, String url) {
        this.filename = filename;
        this.uploader = uploader;
        this.datetime = datetime;
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}