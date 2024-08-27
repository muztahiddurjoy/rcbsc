package com.scientia.science.rcbscassignmentupload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scientia.science.rcbscassignmentupload.Datasets.NoticeDataset;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeViewHolder> {
    ArrayList<NoticeDataset> arrayList;
    Context context;

    public NoticeAdapter(ArrayList<NoticeDataset> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoticeViewHolder(LayoutInflater.from(context).inflate(R.layout.notice_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        NoticeDataset dataset = arrayList.get(position);
        holder.title.setText(dataset.getTitle());
        holder.des.setText(dataset.getDescription());
        holder.cleass.setText(dataset.getCleass());
        holder.time.setText(dataset.getTime());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
