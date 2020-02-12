package com.example.mysmartschool.admin.Kelas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mysmartschool.Guru.SmartInfo.Entitiy_smartinfo;
import com.example.mysmartschool.R;
import com.example.mysmartschool.admin.Guru.ActivityGuru;
import com.example.mysmartschool.admin.Guru.Adapter_Guru_Admin;
import com.example.mysmartschool.admin.Guru.Entity_Guru_Admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.xwray.passwordview.PasswordView;

import java.util.ArrayList;
import java.util.List;

public class ActivityKelas extends AppCompatActivity {

    public static final String ID_GURU = "idGuru";
    public static final String NAMA_GURU = "nama_guru";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Entity_Kelas_Admin> myteacherList;
    private Adapter_Kelas_Admin adapterMyTeacher;

    DatabaseReference databaseReference;

    private FloatingActionButton fab;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelas);

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_kelas_admin);
        myteacherList = new ArrayList<>();
        fab = findViewById(R.id.fab);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Kelas");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAdd();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        mRecyclerView = findViewById(R.id.rv_kelas_admin );
        //        ini untuk menetapkan ukuran recyclerview
        if (mRecyclerView != null){
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(ActivityKelas.this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        myteacherList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myteacherList.clear();

                for (DataSnapshot infoSnapShot : dataSnapshot.getChildren()){
                    Entity_Kelas_Admin myteacher = infoSnapShot.getValue(Entity_Kelas_Admin.class);
                    myteacherList.add(myteacher);
                }

                adapterMyTeacher = new Adapter_Kelas_Admin(ActivityKelas.this,myteacherList);
                mRecyclerView.setAdapter(adapterMyTeacher);
                adapterMyTeacher.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void showDialogAdd(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ActivityKelas.this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_tambah_kelas, null);
        dialogBuilder.setView(dialogView);


        //set judul alert dialog agar tidak bingung
        dialogBuilder.setTitle("Tambah Kelas");
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        final EditText etNama = (EditText)dialogView.findViewById(R.id.et_nama_kelas);
        Button tambahGuru = (Button) dialogView.findViewById(R.id.btntambahKelas);

        //membuat tombol addMurid bekerja dengan semestinya
        tambahGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nams = etNama.getText().toString();

                String idKelas = databaseReference.push().getKey();
                Entity_Kelas_Admin Kelas = new Entity_Kelas_Admin(idKelas , nams);
                databaseReference.child(idKelas).setValue(Kelas);



                alertDialog.dismiss();
            }
        });

    }

}
