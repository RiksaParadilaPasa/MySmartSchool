package com.example.mysmartschool.Guru.Myclass.MyTeacher;

public class Entity_myteacher {

    String idGuru;
    String nip_guru;
    String nama_guru;
    String nomorHp_guru;
    String password;

    public Entity_myteacher(String idGuru, String nip_guru, String nama_guru, String nomorHp_guru, String password) {
        this.idGuru = idGuru;
        this.nip_guru = nip_guru;
        this.nama_guru = nama_guru;
        this.nomorHp_guru = nomorHp_guru;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
