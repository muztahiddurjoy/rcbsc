package com.scientia.science.rcbscassignmentupload;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AssignmentViewHolder extends RecyclerView.ViewHolder {
    public TextView title, upload, submit, subject, cleass;

    public AssignmentViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.assignment_title);
        upload = itemView.findViewById(R.id.upload_date);
        subject = itemView.findViewById(R.id.assignment_subject);
        submit = itemView.findViewById(R.id.assignment_submission);
        cleass = itemView.findViewById(R.id.assignment_cleass);
    }
}
