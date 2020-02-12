package com.example.mysmartschool.Murid.Myclass;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysmartschool.Murid.Myclass.MyTeacher.DataAdapter_myTeachermurid;
import com.example.mysmartschool.Murid.Myclass.MyTeacher.Entity_myteacher;
import com.example.mysmartschool.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyClassFragmentmurid extends Fragment {

    public static final String ID_GURU = "idGuru";
    public static final String NAMA_GURU = "nama_guru";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Entity_myteacher> myteacherList;
    private DataAdapter_myTeachermurid adapterMyTeacher;

    DatabaseReference databaseReference;


    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myclass_murid,container,false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.rv_info);
        myteacherList = new ArrayList<>();



        databaseReference = FirebaseDatabase.getInstance().getReference("Guru");



        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        mRecyclerView = getView().findViewById(R.id.recycler_container_myteacher_activity_main1);
        //        ini untuk menetapkan ukuran recyclerview
        if (mRecyclerView != null){
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new GridLayoutManager(getContext(),2);

        mRecyclerView.setLayoutManager(mLayoutManager);

        myteacherList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myteacherList.clear();

                for (DataSnapshot infoSnapShot : dataSnapshot.getChildren()){
                    Entity_myteacher myteacher = infoSnapShot.getValue(Entity_myteacher.class);
                    myteacherList.add(myteacher);
                }

                adapterMyTeacher = new DataAdapter_myTeachermurid(getContext(),myteacherList);
                mRecyclerView.setAdapter(adapterMyTeacher);
                adapterMyTeacher.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void showDialogAdd(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_tambah_guru, null);
        dialogBuilder.setView(dialogView);


        //set judul alert dialog agar tidak bingung
        dialogBuilder.setTitle("Tambah Guru");
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        final EditText etNip = (EditText)dialogView.findViewById(R.id.et_nip_guru);
        final EditText etNama = (EditText)dialogView.findViewById(R.id.et_nama_guru);
        final EditText etNomor = (EditText)dialogView.findViewById(R.id.et_nomor_guru);
        Button tambahGuru = (Button) dialogView.findViewById(R.id.btntambahGuru);

        //membuat tombol addMurid bekerja dengan semestinya
        tambahGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nips = etNip.getText().toString();
                String nams = etNama.getText().toString();
                String noms = etNomor.getText().toString();



                String idGuru = databaseReference.push().getKey();
                Entity_myteacher myteacher = new Entity_myteacher(idGuru,nips,nams,noms);
                databaseReference.child(idGuru).setValue(myteacher);

                Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();

                alertDialog.dismiss();
            }
        });

    }


}