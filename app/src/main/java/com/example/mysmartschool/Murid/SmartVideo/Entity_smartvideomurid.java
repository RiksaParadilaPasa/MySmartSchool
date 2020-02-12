package com.example.mysmartschool.Murid.SmartVideo;

public class Entity_smartvideomurid {

    String idVideo;
    String judul;
    String mapel;

    public Entity_smartvideomurid(String idVideo, String judul, String mapel) {
        this.idVideo = idVideo;
        this.judul = judul;
        this.mapel = mapel;
    }

    public Entity_smartvideomurid(){}

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getMapel() {
        return mapel;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }
}
