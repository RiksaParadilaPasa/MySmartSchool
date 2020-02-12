package com.example.mysmartschool.Guru.SmartInfo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysmartschool.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

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

    private StorageReference mStorageRef;

    private Button gambarbtn;
    private ImageView gambar;


    public static final int pReqCode = 1;
    public static final int REQUESCODE = 1;
    private Uri imgUri;


    //    onCreate berfungsi untuk saat kita menjalankan program langsung menjalankan yg berada di dalamnya (Pembuatan)

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smart_info, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_info);
        infoList = new ArrayList<>();
        fab = view.findViewById(R.id.fab);

        databaseReference = FirebaseDatabase.getInstance().getReference("Info");
        mStorageRef = FirebaseStorage.getInstance().getReference();


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
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);


        infoList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                infoList.clear();

                for (DataSnapshot infoSnapShot : dataSnapshot.getChildren()) {
                    Entitiy_smartinfo info = infoSnapShot.getValue(Entitiy_smartinfo.class);
                    infoList.add(info);
                }

                Adapter_smartinfo adapter = new Adapter_smartinfo(getContext(), infoList);
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
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);

        infoList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                infoList.clear();

                for (DataSnapshot infoSnapShot : dataSnapshot.getChildren()) {
                    Entitiy_smartinfo info = infoSnapShot.getValue(Entitiy_smartinfo.class);
                    infoList.add(info);
                }

                adapterInfo = new Adapter_smartinfo(getContext(), infoList);
                mRecyclerView.setAdapter(adapterInfo);
                adapterInfo.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void showDialogAdd() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_tambah_info, null);
        dialogBuilder.setView(dialogView);

        //set judul alert dialog agar tidak bingung
        dialogBuilder.setTitle("Tambah Info");
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        gambar = dialogView.findViewById(R.id.imageContainer);
        gambarbtn = dialogView.findViewById(R.id.btn_image);

        final EditText etKirim = (EditText) dialogView.findViewById(R.id.et_kirim);
        final EditText etJudul = (EditText) dialogView.findViewById(R.id.et_judul);
        final EditText etDate = (EditText) dialogView.findViewById(R.id.et_date);
        final EditText etAbout = (EditText) dialogView.findViewById(R.id.et_about);
        final Button btnGambar = (Button) dialogView.findViewById(R.id.btn_image);
        Button tambahInfo = (Button) dialogView.findViewById(R.id.tambahInfo);

        gambarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 22) {
                    checkAndRequestForPermissions();
                } else {
                    openGallery();
                }
            }
        });


        //membuat tombol addMurid bekerja dengan semestinya
        tambahInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pengirim = etKirim.getText().toString();
                String penjudul = etJudul.getText().toString();
                String tanggal = etDate.getText().toString();
                String perinci = etAbout.getText().toString();

                StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("gambar_info");
                final StorageReference gambarBerita = mStorageRef.child(imgUri.getLastPathSegment());
                gambarBerita.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                        gambarBerita.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Task<Uri> uriGambar = taskSnapshot.getStorage().getDownloadUrl();
                                while (!uriGambar.isComplete()) ;
                                Uri url = uriGambar.getResult();

                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                                Date date = Calendar.getInstance().getTime();
                                String currentDate = dateFormat.format(date);
                                String idInfo = databaseReference.push().getKey();
                                Entitiy_smartinfo Info = new Entitiy_smartinfo(idInfo, pengirim, currentDate, penjudul, perinci, url.toString());
                                databaseReference.child(idInfo).setValue(Info);


                            }
                        });
                    }
                });


                alertDialog.dismiss();


            }
        });

    }


    private void checkAndRequestForPermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(getActivity(), "Pleast accept for required permission", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, pReqCode);
            }
        } else {
            openGallery();
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {
            imgUri = data.getData();
            gambar.setImageURI(imgUri);
        }
    }


}
