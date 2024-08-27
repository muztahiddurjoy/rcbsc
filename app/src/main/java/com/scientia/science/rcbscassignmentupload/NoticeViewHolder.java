package com.scientia.science.rcbscassignmentupload;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoticeViewHolder extends RecyclerView.ViewHolder {
    public TextView title,des,cleass,time;
    public NoticeViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.notice_title);
        des = itemView.findViewById(R.id.notice_desc);
        cleass = itemView.findViewById(R.id.class_notice);
        time = itemView.findViewById(R.id.time_notice);
    }
}
