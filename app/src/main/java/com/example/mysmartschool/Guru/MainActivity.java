package com.example.mysmartschool.Guru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mysmartschool.Guru.HomeFragment.HomeFragment;
import com.example.mysmartschool.Guru.MyWork.MyWorkFragment;
import com.example.mysmartschool.Guru.SmartInfo.SmartInfoFragment;
import com.example.mysmartschool.Guru.SmartVideo.SmartVideoFragment;
import com.example.mysmartschool.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new HomeFragment());
        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
//        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.menu_home:
                fragment = new HomeFragment();
                break;
            case R.id.menu_myvideo:
                fragment = new SmartVideoFragment();
                break;
            case R.id.menu_smartinfo:
                fragment = new SmartInfoFragment();
                break;
            case R.id.menu_mywork:
                fragment = new MyWorkFragment();
                break;
        }

        return loadFragment(fragment);
    }


    private boolean loadFragment(Fragment fragment){

        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame,fragment)
                    .commit();
            return true;
        }

        return false;
    }


}
