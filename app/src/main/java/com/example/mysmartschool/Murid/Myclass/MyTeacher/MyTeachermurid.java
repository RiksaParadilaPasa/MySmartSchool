package com.example.mysmartschool.Murid.Myclass.MyTeacher;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysmartschool.R;

public class MyTeachermurid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_murid);

        //        ini untuk deklarasikan atau inisialisasi ID dari XML

        TextView nama = (TextView)findViewById(R.id.tv_name_guru);
        TextView nip = (TextView)findViewById(R.id.tv_nip_guru);
        TextView nomor = (TextView)findViewById(R.id.tv_nomor_guru);



//        ini untuk mengambil data yg dikirim dari ADAPTER (ONBINDVIEWHOLDER)

        if(getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();

            if(bundle != null){
                nama.setText(bundle.getString("nama"));
                nip.setText(bundle.getString("nip"));
                nomor.setText(bundle.getString("nomorhp"));

            } else{
                nama.setText(getIntent().getStringExtra("nama"));
                nip.setText(getIntent().getStringExtra("nip"));
                nomor.setText(getIntent().getStringExtra("nomorhp"));
            }

        }

//        String juduls = getIntent().getStringExtra("judul");
//        String pengirims = getIntent().getStringExtra("dari");
//        String dates = getIntent().getStringExtra("tgl");
//        String abouts = getIntent().getStringExtra("rinci");

//        INI UNTUK menset atau menampilkan data di atas pada XML

//        judul.setText(juduls);
//        pengirim.setText(pengirims);
//        date.setText(dates);
//        about.setText(abouts);

    }
}
