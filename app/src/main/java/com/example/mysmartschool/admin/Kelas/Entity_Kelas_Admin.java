package com.example.mysmartschool.admin.Kelas;

public class Entity_Kelas_Admin {

    String idKelas;
    String NamaKelas;

    public Entity_Kelas_Admin(String idKelas, String namaKelas) {
        this.idKelas = idKelas;
        NamaKelas = namaKelas;
    }

    public Entity_Kelas_Admin(){}

    public String getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(String idKelas) {
        this.idKelas = idKelas;
    }

    public String getNamaKelas() {
        return NamaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        NamaKelas = namaKelas;
    }
}
