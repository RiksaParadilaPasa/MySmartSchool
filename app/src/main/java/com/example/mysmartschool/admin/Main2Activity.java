package com.example.mysmartschool.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mysmartschool.R;
import com.example.mysmartschool.admin.Admin.ActivityAdmin;
import com.example.mysmartschool.admin.Guru.ActivityGuru;
import com.example.mysmartschool.admin.Kelas.ActivityKelas;
import com.example.mysmartschool.admin.Mapel.ActivityMapel;
import com.example.mysmartschool.admin.Murid.ActivityMurid;

public class Main2Activity extends AppCompatActivity {

    Button bGuru,bSiswa,bKelas,bAdmin,bMapel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        buttonHomeAdmin();

    }


    public void buttonHomeAdmin(){
        bGuru = (Button)findViewById(R.id.btnGuruAdmin);
        bSiswa = (Button)findViewById(R.id.btnSiswaAdmin);
        bKelas = (Button)findViewById(R.id.btnKelasAdmin);
        bAdmin = (Button)findViewById(R.id.btnAdminAdmin);
        bMapel = (Button)findViewById(R.id.btnMapelAdmin);

        bGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, ActivityGuru.class));

            }
        });

        bSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(Main2Activity.this, ActivityMurid.class));
            }
        });

        bKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, ActivityKelas.class));
            }
        });

        bAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, ActivityAdmin.class));
            }
        });

        bMapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, ActivityMapel.class));
            }
        });

    }


}
