package com.example.mysmartschool.HomeFragment;

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

import com.example.mysmartschool.Myclass.MyClassFragment;
import com.example.mysmartschool.Profile.ProfileFragment;
import com.example.mysmartschool.R;

import java.util.zip.Inflater;

public class HomeFragment extends Fragment {

    Button btn_myclass;
    ImageButton btn_profile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,null);

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

    }



}
