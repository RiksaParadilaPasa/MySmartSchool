package com.example.mysmartschool.Guru.SmartInfo;

public class Entitiy_smartinfo {

    String idInfo;
    String pengirim;
    String date;
    String judul;
    String about;
    String url;


//ini fungsinya seperti constructor


    public Entitiy_smartinfo(String idInfo, String pengirim, String date, String judul, String about, String url) {
        this.idInfo = idInfo;
        this.pengirim = pengirim;
        this.date = date;
        this.judul = judul;
        this.about = about;
        this.url = url;
    }

    public Entitiy_smartinfo(){}


    public String getIdInfo() {
        return idInfo;
    }

    public void setIdInfo(String idInfo) {
        this.idInfo = idInfo;
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
