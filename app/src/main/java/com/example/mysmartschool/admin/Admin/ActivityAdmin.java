package com.example.mysmartschool.admin.Admin;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mysmartschool.R;
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
import com.example.mysmartschool.admin.Admin.ActivityAdmin;
import com.example.mysmartschool.admin.Admin.Adapter_Admin_Admin;
import com.example.mysmartschool.admin.Admin.Entity_Admin_Admin;

import java.util.ArrayList;
import java.util.List;

public class ActivityAdmin extends AppCompatActivity {

    public static final String ID_GURU = "idGuru";
    public static final String NAMA_GURU = "nama_guru";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Entity_Admin_Admin> myteacherList;
    private Adapter_Admin_Admin adapterMyTeacher;

    DatabaseReference databaseReference;

    private FloatingActionButton fab;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_admin_admin);
        myteacherList = new ArrayList<>();
        FloatingActionButton fab = findViewById(R.id.fab);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User").child("Admin");

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

        mRecyclerView = findViewById(R.id.rv_admin_admin );
        //        ini untuk menetapkan ukuran recyclerview
        if (mRecyclerView != null){
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(ActivityAdmin.this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        myteacherList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myteacherList.clear();

                for (DataSnapshot infoSnapShot : dataSnapshot.getChildren()){
                    Entity_Admin_Admin myteacher = infoSnapShot.getValue(Entity_Admin_Admin.class);
                    myteacherList.add(myteacher);
                }

                adapterMyTeacher = new Adapter_Admin_Admin(ActivityAdmin.this,myteacherList);
                mRecyclerView.setAdapter(adapterMyTeacher);
                adapterMyTeacher.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void showDialogAdd(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ActivityAdmin.this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_tambah_admin, null);
        dialogBuilder.setView(dialogView);


        //set judul alert dialog agar tidak bingung
        dialogBuilder.setTitle("Tambah Admin");
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        final EditText etNama = (EditText)dialogView.findViewById(R.id.et_nama_admin);
        final EditText etUsername = (EditText)dialogView.findViewById(R.id.et_username_admin);
        final EditText etPassword = (PasswordView)dialogView.findViewById(R.id.et_pass_admin);
        Button tambahGuru = (Button) dialogView.findViewById(R.id.btntambahAdmin);

        //membuat tombol addMurid bekerja dengan semestinya
        tambahGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nams = etNama.getText().toString();
                String username = etUsername.getText().toString()+"@gmail.com";
                String pass = etPassword.getText().toString();

                mAuth.createUserWithEmailAndPassword(username, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String id = mAuth.getCurrentUser().getUid();
                        Entity_Admin_Admin myteacher = new Entity_Admin_Admin(id,nams,username,pass);
                        databaseReference.child(id).setValue(myteacher);
                        Toast.makeText(ActivityAdmin.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    }
                });



                alertDialog.dismiss();
            }
        });

    }

}
