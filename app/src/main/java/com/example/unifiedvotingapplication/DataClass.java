package com.example.unifiedvotingapplication;

public class DataClass {
    private String dataImage;
    private String voted;

    public String getVoted() {
        return voted;
    }

    public void setVoted(String voted) {
        this.voted = voted;
    }

    private String title;
    private String des;
    private String conduc;
    private String conducMobile;
    private String conducEmail;
    private String pass;
    private String key;
    private String psd;
    private String ped;
    private String frd;
    private String fcd;
    private String pst;
    private String pet;
    private String frt;
    private String fct;
    private String cbr;
    private String cyr;
    private String cg;
    private String cba;
    private String vbr;
    private String vyr;
    private String vg;
    private String vba;

    public String getCbr() {
        return cbr;
    }

    public String getCyr() {
        return cyr;
    }

    public String getCg() {
        return cg;
    }

    public String getCba() {
        return cba;
    }

    public String getVbr() {
        return vbr;
    }

    public String getVyr() {
        return vyr;
    }

    public String getVg() {
        return vg;
    }

    public String getVba() {
        return vba;
    }

    public String getPsd() {
        return psd;
    }

    public String getPed() {
        return ped;
    }

    public String getFrd() {
        return frd;
    }

    public String getFcd() {
        return fcd;
    }

    public String getPst() {
        return pst;
    }

    public String getPet() {
        return pet;
    }

    public String getFrt() {
        return frt;
    }

    public String getFct() {
        return fct;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getDes() {
        return des;
    }

    public String getConduc() {
        return conduc;
    }

    public String getConducMobile() {
        return conducMobile;
    }

    public String getConducEmail() {
        return conducEmail;
    }

    public String getPass() {
        return pass;
    }

    public String getDataImage() {
        return dataImage;
    }

    public DataClass(String title, String des, String conduc, String conducMobile, String conducEmail, String pass, String dataImage,String frd,String fcd,String frt,String fct,String psd,String ped,String pst,String pet,String cbr,String cyr,String cg,String cba,String vbr,String vyr,String vg,String vba) {
        this.dataImage=dataImage;
        this.title = title;
        this.des = des;
        this.conduc = conduc;
        this.conducMobile = conducMobile;
        this.conducEmail = conducEmail;
        this.pass = pass;
        this.frd=frd;
        this.fcd=fcd;
        this.frt=frt;
        this.fct=fct;
        this.psd=psd;
        this.ped=ped;
        this.pst=pst;
        this.pet=pet;
        this.cbr=cbr;
        this.cyr=cyr;
        this.cg=cg;
        this.cba=cba;
        this.vbr=vbr;
        this.vyr=vyr;
        this.vg=vg;
        this.vba=vba;
        this.voted=":";

    }

    public DataClass(){

    }
}