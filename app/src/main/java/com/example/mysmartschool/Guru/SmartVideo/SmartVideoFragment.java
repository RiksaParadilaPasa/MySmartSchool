package com.example.mysmartschool.Guru.SmartVideo;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysmartschool.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SmartVideoFragment extends Fragment {

    public static final String ID_VIDEO = "idVideo";
    public static final String JUDUL_VIDEO = "judul";
    public static final String MAPEL_VIDEO = "video";

    //    ini untuk inisialisasi
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Entity_smartvideo> smartvideoList;
    private Adapter_smartvideo adapterSmartvideo;
    DatabaseReference databaseReference;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smart_video,container,false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.rv_video);
        smartvideoList = new ArrayList<>();
        fab = view.findViewById(R.id.fab);


        databaseReference = FirebaseDatabase.getInstance().getReference("Video");

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

        mRecyclerView = getView().findViewById(R.id.rv_video);

        //        ini untuk menetapkan ukuran recyclerview
        if (mRecyclerView != null){
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);


        smartvideoList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                smartvideoList.clear();

                for (DataSnapshot infoSnapShot : dataSnapshot.getChildren()){
                    Entity_smartvideo video = infoSnapShot.getValue(Entity_smartvideo.class);
                    smartvideoList.add(video);
                }

                adapterSmartvideo = new Adapter_smartvideo(getContext(),smartvideoList);
                mRecyclerView.setAdapter(adapterSmartvideo);
                adapterSmartvideo.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void showDialogAdd(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_tambah_video, null);
        dialogBuilder.setView(dialogView);


        //set judul alert dialog agar tidak bingung
        dialogBuilder.setTitle("Tambah Info");
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        final EditText etJudul = (EditText)dialogView.findViewById(R.id.tambah_video_judul);
        final EditText etMapel = (EditText)dialogView.findViewById(R.id.tambah_video_mapel);
        Button tambahInfo = (Button) dialogView.findViewById(R.id.btnTambahVideo);

        //membuat tombol addMurid bekerja dengan semestinya
        tambahInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String penjudul = etJudul.getText().toString();
                String pemapel = etMapel.getText().toString();

                String idVideo = databaseReference.push().getKey();
                Entity_smartvideo Info = new Entity_smartvideo(idVideo , penjudul, pemapel);
                databaseReference.child(idVideo).setValue(Info);

                Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();

                alertDialog.dismiss();
            }
        });

    }

}
