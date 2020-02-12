package com.example.mysmartschool.Murid.SmartInfo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysmartschool.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

//        ini untuk deklarasikan atau inisialisasi ID dari XML

        TextView judul = (TextView)findViewById(R.id.tv_judul);
        TextView pengirim = (TextView)findViewById(R.id.tv_pengirim);
        TextView date = (TextView)findViewById(R.id.tv_tanggal);
        TextView about = (TextView)findViewById(R.id.tv_about);


//        ini untuk mengambil data yg dikirim dari ADAPTER (ONBINDVIEWHOLDER)

        if(getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();

            if(bundle != null){
                judul.setText(bundle.getString("judul"));
                pengirim.setText(bundle.getString("dari"));
                date.setText(bundle.getString("tgl"));
                about.setText(bundle.getString("rinci"));

            } else{
                judul.setText(getIntent().getStringExtra("judul"));
                pengirim.setText(getIntent().getStringExtra("dari"));
                date.setText(getIntent().getStringExtra("tgl"));
                about.setText(getIntent().getStringExtra("rinci"));
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
