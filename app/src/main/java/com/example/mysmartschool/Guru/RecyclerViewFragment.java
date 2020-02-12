package com.example.mysmartschool.Guru;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysmartschool.R;

public class RecyclerViewFragment<VH extends RecyclerView.ViewHolder> extends Fragment {

    private String title;
    private RecyclerView.Adapter<VH> adapter;
    private RecyclerView.LayoutManager layoutManager;

    public RecyclerViewFragment(String title, RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter) {
        this.title = title;
        this.adapter = adapter;
        this.layoutManager = layoutManager;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }
}