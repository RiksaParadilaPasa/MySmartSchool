package com.example.mysmartschool.Murid.HomeFragment;

import android.content.Intent;
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

import com.example.mysmartschool.Guru.Myclass.MyClassFragment;
import com.example.mysmartschool.Guru.Profile.ProfileFragment;
import com.example.mysmartschool.LoginActivity;
import com.example.mysmartschool.Murid.Myclass.MyClassFragmentmurid;
import com.example.mysmartschool.Murid.Profile.ProfileFragmentmurid;
import com.example.mysmartschool.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeFragmentmurid extends Fragment {

    Button btn_myclass;
    ImageButton btn_profile;
    Button logout;

    FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_murid,null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        logout = (Button)view.findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

        btn_myclass = (Button)view.findViewById(R.id.home_btnMyclass);
        btn_myclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fr = new MyClassFragmentmurid();
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
                Fragment fr = new ProfileFragmentmurid();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_frame, fr);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

    }



}
