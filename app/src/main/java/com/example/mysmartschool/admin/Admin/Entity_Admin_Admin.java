package com.example.mysmartschool.admin.Admin;

public class Entity_Admin_Admin {

    String idAdmin;
    String nama_admin;
    String username;
    String password;

    public Entity_Admin_Admin(String idAdmin, String nama_admin, String username, String password) {
        this.idAdmin = idAdmin;
        this.nama_admin = nama_admin;
        this.username = username;
        this.password = password;
    }

    public Entity_Admin_Admin(){}

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNama_admin() {
        return nama_admin;
    }

    public void setNama_admin(String nama_admin) {
        this.nama_admin = nama_admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
