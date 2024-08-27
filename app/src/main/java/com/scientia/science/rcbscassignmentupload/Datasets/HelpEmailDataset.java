package com.scientia.science.rcbscassignmentupload.Datasets;

public class HelpEmailDataset {
    String email;
    String description;
    String imageurl;

    public HelpEmailDataset(String email, String description, String imageurl) {
        this.email = email;
        this.description = description;
        this.imageurl = imageurl;
    }

    public HelpEmailDataset() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
