package com.example.unifiedvotingapplication;

public class DataClass3 {
    private String dataImage;
    private String achivements;
    private String objectives;
    private String user;
    private String election;
    private String vote ="0";

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    private String promises;
    private String others;

    public String getAchivements() {
        return achivements;
    }

    public String getObjectives() {
        return objectives;
    }

    public String getPromises() {
        return promises;
    }

    public String getOthers() {
        return others;
    }

    public String getElection() {
        return election;
    }

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


    public String getDataImage() {
        return dataImage;
    }

    public DataClass3(String user,String election, String objectives, String promises,String achivements,String others, String dataImage,String vote) {
        this.dataImage=dataImage;
        this.user = user;
        this.objectives = objectives;
        this.promises = promises;
        this.achivements = achivements;
        this.others = others;
        this.election = election;
        this.vote=vote;

    }

    public DataClass3(){

    }
}