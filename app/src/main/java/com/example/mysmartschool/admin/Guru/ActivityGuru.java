package com.example.mysmartschool.admin.Guru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mysmartschool.Guru.Myclass.MyTeacher.DataAdapter_myTeacher;
import com.example.mysmartschool.Guru.Myclass.MyTeacher.Entity_myteacher;
import com.example.mysmartschool.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.xwray.passwordview.PasswordView;

import java.util.ArrayList;
import java.util.List;

public class ActivityGuru extends AppCompatActivity {

    public static final String ID_GURU = "idGuru";
    public static final String NAMA_GURU = "nama_guru";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Entity_Guru_Admin> myteacherList;
    private Adapter_Guru_Admin adapterMyTeacher;

    DatabaseReference databaseReference;

    private FloatingActionButton fab;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guru);

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_guru_admin);
        myteacherList = new ArrayList<>();
        FloatingActionButton fab = findViewById(R.id.fab);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User").child("Guru");

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

        mRecyclerView = findViewById(R.id.rv_guru_admin );
        //        ini untuk menetapkan ukuran recyclerview
        if (mRecyclerView != null){
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(ActivityGuru.this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        myteacherList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myteacherList.clear();

                for (DataSnapshot infoSnapShot : dataSnapshot.getChildren()){
                    Entity_Guru_Admin myteacher = infoSnapShot.getValue(Entity_Guru_Admin.class);
                    myteacherList.add(myteacher);
                }

                adapterMyTeacher = new Adapter_Guru_Admin(ActivityGuru.this,myteacherList);
                mRecyclerView.setAdapter(adapterMyTeacher);
                adapterMyTeacher.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void showDialogAdd(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ActivityGuru.this);
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
        final EditText etPassword = (PasswordView)dialogView.findViewById(R.id.et_pass_guru);
        Button tambahGuru = (Button) dialogView.findViewById(R.id.btntambahGuru);

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
                        Entity_Guru_Admin myteacher = new Entity_Guru_Admin(id,nips,nams,noms,pass);
                        databaseReference.child(id).setValue(myteacher);
                        Toast.makeText(ActivityGuru.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    }
                });



                alertDialog.dismiss();
            }
        });

    }

}
