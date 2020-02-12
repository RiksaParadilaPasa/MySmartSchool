package com.example.mysmartschool.admin.Murid;

public class Entity_Murid_Admin {

    String idMurid;
    String nis_murid;
    String nama_murid;
    String nomorHp_murid;
    String password;

    public Entity_Murid_Admin(String idMurid, String nis_murid, String nama_murid, String nomorHp_murid, String password) {
        this.idMurid = idMurid;
        this.nis_murid = nis_murid;
        this.nama_murid = nama_murid;
        this.nomorHp_murid = nomorHp_murid;
        this.password = password;
    }

    public Entity_Murid_Admin(){}

    public String getIdMurid() {
        return idMurid;
    }

    public void setIdMurid(String idMurid) {
        this.idMurid = idMurid;
    }

    public String getNis_murid() {
        return nis_murid;
    }

    public void setNis_murid(String nis_murid) {
        this.nis_murid = nis_murid;
    }

    public String getNama_murid() {
        return nama_murid;
    }

    public void setNama_murid(String nama_murid) {
        this.nama_murid = nama_murid;
    }

    public String getNomorHp_murid() {
        return nomorHp_murid;
    }

    public void setNomorHp_murid(String nomorHp_murid) {
        this.nomorHp_murid = nomorHp_murid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
