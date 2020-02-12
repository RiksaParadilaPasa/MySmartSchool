package com.example.mysmartschool.Murid.Myclass.MyTeacher;

public class Entity_myteacher {

    String idGuru;
    String nip_guru;
    String nama_guru;
    String nomorHp_guru;


    public Entity_myteacher(String idGuru, String nip_guru, String nama_guru, String nomorHp_guru) {
        this.idGuru = idGuru;
        this.nip_guru = nip_guru;
        this.nama_guru = nama_guru;
        this.nomorHp_guru = nomorHp_guru;
    }

    public Entity_myteacher(){}

    public String getIdGuru() {
        return idGuru;
    }

    public void setIdGuru(String idGuru) {
        this.idGuru = idGuru;
    }

    public String getNip_guru() {
        return nip_guru;
    }

    public void setNip_guru(String nip_guru) {
        this.nip_guru = nip_guru;
    }

    public String getNama_guru() {
        return nama_guru;
    }

    public void setNama_guru(String nama_guru) {
        this.nama_guru = nama_guru;
    }

    public String getNomorHp_guru() {
        return nomorHp_guru;
    }

    public void setNomorHp_guru(String nomorHp_guru) {
        this.nomorHp_guru = nomorHp_guru;
    }
}
