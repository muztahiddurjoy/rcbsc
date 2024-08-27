package com.scientia.science.rcbscassignmentupload.Datasets;

public class SignUpDataset {
    String name;
    String email;
    String number;
    String cleass;
    String roll;
    String id;
    String section;
    String password;

    public SignUpDataset() {
    }

    public SignUpDataset(String name, String email, String number, String cleass, String roll, String id, String section, String password) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.cleass = cleass;
        this.roll = roll;
        this.id = id;
        this.section = section;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCleass() {
        return cleass;
    }

    public void setCleass(String cleass) {
        this.cleass = cleass;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
