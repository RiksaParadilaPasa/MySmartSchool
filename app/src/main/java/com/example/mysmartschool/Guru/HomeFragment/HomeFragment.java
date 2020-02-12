package com.example.mysmartschool.Guru.HomeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysmartschool.Guru.Myclass.MyClassFragment;
import com.example.mysmartschool.Guru.Profile.ProfileFragment;
import com.example.mysmartschool.Murid.SmartInfo.Adapter_smartinfomurid;
import com.example.mysmartschool.Murid.SmartInfo.Entitiy_smartinfo;
import com.example.mysmartschool.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    Button btn_myclass;
    ImageButton btn_profile;

    //digunakan untuk memnindahkan data dari 1 activity ke activity lain
    public static final String ID_INFO = "idInfo";
    public static final String NAMA_INFO = "namaInfo";
    public static final String TINGKATAN_INFO = "tingkatanInfo";

    //    ini untuk inisialisasi
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Entitiy_smartinfo> infoList;
    private Adapter_smartinfomurid adapterInfo;
    DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.rv_info);
        infoList = new ArrayList<>();



        databaseReference = FirebaseDatabase.getInstance().getReference("Info");



        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_myclass = (Button)view.findViewById(R.id.home_btnMyclass);
        btn_myclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fr = new MyClassFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_frame, fr);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


        btn_profile = (ImageButton)view.findViewById(R.id.home_btnProfile);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fr = new ProfileFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_frame, fr);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


        mRecyclerView = getView().findViewById(R.id.home_recyclerview);

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

                Adapter_smartinfomurid adapter = new Adapter_smartinfomurid(getContext(),infoList);
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

        mRecyclerView = getView().findViewById(R.id.home_recyclerview);
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

                adapterInfo = new Adapter_smartinfomurid(getContext(),infoList);
                mRecyclerView.setAdapter(adapterInfo);
                adapterInfo.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}
