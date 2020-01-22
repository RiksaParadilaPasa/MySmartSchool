package com.example.mysmartschool.MyWork;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysmartschool.R;

import java.util.List;

public class MataPelajaranAdapter extends RecyclerView.Adapter<MataPelajaranAdapter.ViewHolder> {

    private List<String> matpel;

    public MataPelajaranAdapter(List<String> data) {
        this.matpel = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_mywork_nilai, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMatpel.setText(matpel.get(position));
    }

    @Override
    public int getItemCount() {
        return matpel.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMatpel;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
