package com.example.mysmartschool.admin.Murid;

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

public class ActivityMurid extends AppCompatActivity {

    public static final String ID_GURU = "idGuru";
    public static final String NAMA_GURU = "nama_guru";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Entity_Murid_Admin> muridAdminList;
    private Adapter_Murid_Admin adapterMuridAdmin;

    DatabaseReference databaseReference;

    private FloatingActionButton fab;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_murid);

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_murid_admin);
        muridAdminList = new ArrayList<>();
        FloatingActionButton fab = findViewById(R.id.fab);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User").child("Murid");

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

        mRecyclerView = findViewById(R.id.rv_murid_admin );
        //        ini untuk menetapkan ukuran recyclerview
        if (mRecyclerView != null){
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(ActivityMurid.this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        muridAdminList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                muridAdminList.clear();

                for (DataSnapshot infoSnapShot : dataSnapshot.getChildren()){
                    Entity_Murid_Admin myteacher = infoSnapShot.getValue(Entity_Murid_Admin.class);
                    muridAdminList.add(myteacher);
                }

                adapterMuridAdmin = new Adapter_Murid_Admin(ActivityMurid.this,muridAdminList);
                mRecyclerView.setAdapter(adapterMuridAdmin);
                adapterMuridAdmin.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void showDialogAdd(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ActivityMurid.this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_tambah_murid, null);
        dialogBuilder.setView(dialogView);


        //set judul alert dialog agar tidak bingung
        dialogBuilder.setTitle("Tambah Murid");
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        final EditText etNip = (EditText)dialogView.findViewById(R.id.et_nis_murid);
        final EditText etNama = (EditText)dialogView.findViewById(R.id.et_nama_murid);
        final EditText etNomor = (EditText)dialogView.findViewById(R.id.et_nomor_murid);
        final EditText etPassword = (PasswordView)dialogView.findViewById(R.id.et_pass_murid);
        Button tambahGuru = (Button) dialogView.findViewById(R.id.btntambahMurid);

        //membuat tombol addMurid bekerja dengan semestinya
        tambahGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nips = etNip.getText().toString()+"@gmail.com";
                String nams = etNama.getText().toString();
                String noms = etNomor.getText().toString();
                String pass = etPassword.getText().toString();

                mAuth.createUserWithEmailAndPassword(nips, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String id = mAuth.getCurrentUser().getUid();
                        Entity_Murid_Admin myteacher = new Entity_Murid_Admin(id,nips,nams,noms,pass);
                        databaseReference.child(id).setValue(myteacher);
                        Toast.makeText(ActivityMurid.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    }
                });



                alertDialog.dismiss();
            }
        });

    }

}
