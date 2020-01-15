package com.example.mysmartschool.SmartInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysmartschool.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SmartInfoFragment extends Fragment {

    //digunakan untuk memnindahkan data dari 1 activity ke activity lain
    public static final String ID_INFO = "idInfo";
    public static final String NAMA_INFO = "namaInfo";
    public static final String TINGKATAN_INFO = "tingkatanInfo";

    //    ini untuk inisialisasi
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Entitiy_smartinfo> infoList;
    private Adapter_smartinfo adapterInfo;
    DatabaseReference databaseReference;
    private FloatingActionButton fab;

    //    onCreate berfungsi untuk saat kita menjalankan program langsung menjalankan yg berada di dalamnya (Pembuatan)

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smart_info,container,false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.rv_info);
        infoList = new ArrayList<>();
        FloatingActionButton fab = view.findViewById(R.id.fab);


        databaseReference = FirebaseDatabase.getInstance().getReference("Info");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAdd();
            }
        });

        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = getView().findViewById(R.id.rv_info);

        //        ini untuk menetapkan ukuran recyclerview
        if (mRecyclerView != null){
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);


        infoList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                infoList.clear();

                for (DataSnapshot infoSnapShot : dataSnapshot.getChildren()){
                    Entitiy_smartinfo info = infoSnapShot.getValue(Entitiy_smartinfo.class);
                    infoList.add(info);
                }

            Adapter_smartinfo adapter = new Adapter_smartinfo(getContext(),infoList);
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        mRecyclerView = getView().findViewById(R.id.rv_info);
        //        ini untuk menetapkan ukuran recyclerview
        if (mRecyclerView != null){
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);

        infoList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                infoList.clear();

                for (DataSnapshot infoSnapShot : dataSnapshot.getChildren()){
                    Entitiy_smartinfo info = infoSnapShot.getValue(Entitiy_smartinfo.class);
                    infoList.add(info);
                }

                Adapter_smartinfo adapter = new Adapter_smartinfo(getContext(),infoList);
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void showDialogAdd(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_tambah_info, null);
        dialogBuilder.setView(dialogView);


        //set judul alert dialog agar tidak bingung
        dialogBuilder.setTitle("Tambah Info");
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        final EditText etKirim = (EditText)dialogView.findViewById(R.id.et_kirim);
        final EditText etJudul = (EditText)dialogView.findViewById(R.id.et_judul);
        final EditText etDate = (EditText)dialogView.findViewById(R.id.et_date);
        final EditText etAbout = (EditText)dialogView.findViewById(R.id.et_about);
        Button tambahInfo = (Button) dialogView.findViewById(R.id.tambahInfo);

        //membuat tombol addMurid bekerja dengan semestinya
        tambahInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pengirim = etKirim.getText().toString();
                String penjudul = etJudul.getText().toString();
                String tanggal = etDate.getText().toString();
                String perinci = etAbout.getText().toString();

                String idInfo = databaseReference.push().getKey();
                Entitiy_smartinfo Info = new Entitiy_smartinfo(idInfo , pengirim, penjudul,tanggal,perinci);
                databaseReference.child(idInfo).setValue(Info);

                Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();

                alertDialog.dismiss();
            }
        });

    }

}
