package com.scientia.science.rcbscassignmentupload;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scientia.science.rcbscassignmentupload.Datasets.ContactDataset;

import java.util.ArrayList;

public class CallTeacherAdapter extends RecyclerView.Adapter<CallTeacherAdapter.CallTeacherViewHolder> {
    ArrayList<ContactDataset> arrayList;
    Context context;

    public CallTeacherAdapter(ArrayList<ContactDataset> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CallTeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CallTeacherViewHolder(LayoutInflater.from(context).inflate(R.layout.call_teacher_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CallTeacherViewHolder holder, int position) {
        ContactDataset dataset = arrayList.get(position);
        holder.nameCall.setText(dataset.getName());
        holder.callIcon.setOnClickListener((v)->{
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+dataset.getPhonenum()));
            try{
                context.startActivity(intent);
            }
            catch (Exception e){
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CallTeacherViewHolder extends RecyclerView.ViewHolder {
        public TextView nameCall;
        public ImageButton callIcon;

        public CallTeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCall = itemView.findViewById(R.id.name_teacher_call);
            callIcon = itemView.findViewById(R.id.call_button_teacher);
        }
    }
}
