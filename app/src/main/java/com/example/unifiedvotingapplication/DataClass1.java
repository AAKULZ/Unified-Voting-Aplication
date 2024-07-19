package com.example.unifiedvotingapplication;

public class DataClass1 {
    private String dataImage;
    private String admin;

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    private String user;
    private String id;
    private String dob;
    private String mobile;
    private String email;
    private String gender;
    private String batch;
    private String pass;
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getUser() {
        return user;
    }

    public String getId() {
        return id;
    }

    public String getDob() {
        return dob;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }
    public String getGender() {
        return gender;
    }

    public String getBatch() {
        return batch;
    }

    public String getPass() {
        return pass;
    }

    public String getDataImage() {
        return dataImage;
    }

    public DataClass1(String user, String id, String dob,String gender,String batch,String mobile,String email, String pass, String dataImage) {
        this.dataImage=dataImage;
        this.user = user;
        this.id = id;
        this.dob = dob;
        this.mobile = mobile;
        this.email = email;
        this.pass = pass;
        this.gender=gender;
        this.batch=batch;
        this.admin="false";

    }

    public DataClass1(){

    }
}