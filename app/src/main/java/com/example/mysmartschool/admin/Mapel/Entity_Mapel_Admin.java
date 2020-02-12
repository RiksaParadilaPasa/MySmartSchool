package com.example.mysmartschool.admin.Mapel;

public class Entity_Mapel_Admin {

    String idMapel;
    String Nam_Mapel;

    public Entity_Mapel_Admin(String idMapel, String nam_Mapel) {
        this.idMapel = idMapel;
        Nam_Mapel = nam_Mapel;
    }

    public Entity_Mapel_Admin(){}

    public String getIdMapel() {
        return idMapel;
    }

    public void setIdMapel(String idMapel) {
        this.idMapel = idMapel;
    }

    public String getNam_Mapel() {
        return Nam_Mapel;
    }

    public void setNam_Mapel(String nam_Mapel) {
        Nam_Mapel = nam_Mapel;
    }
}
