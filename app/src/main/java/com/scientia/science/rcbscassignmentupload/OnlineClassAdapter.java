package com.scientia.science.rcbscassignmentupload;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.scientia.science.rcbscassignmentupload.Datasets.OnlineClassDataset;

import java.util.ArrayList;

public class OnlineClassAdapter extends RecyclerView.Adapter<OnlineClassAdapter.OnlineClassVH> {
    ArrayList<OnlineClassDataset> arrayList;
    ArrayList<String> keys;
    Context context;

    public OnlineClassAdapter(ArrayList<OnlineClassDataset> arrayList, ArrayList<String> keys, Context context) {
        this.arrayList = arrayList;
        this.keys = keys;
        this.context = context;
    }

    @NonNull
    @Override
    public OnlineClassVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnlineClassVH(LayoutInflater.from(context).inflate(R.layout.online_row, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnlineClassVH holder, int position) {
            OnlineClassDataset dataset = arrayList.get(position);
            holder.title.setText(dataset.getTitle());
            holder.topic.setText(dataset.getTopic());
            holder.subject.setText(dataset.getSubject());
            holder.teacher.setText(dataset.getTeacher());
            holder.time.setText(dataset.getTime());
            holder.meetingpass.setText(dataset.getMeetingpass());
            holder.meetingid.setText(dataset.getMeetingid());
            holder.join.setOnClickListener((v)->{
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(dataset.getMeetinglink()));
                    context.startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(context, "দুঃখিত লিংকটি খোলা যায়নি", Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class OnlineClassVH extends RecyclerView.ViewHolder {
       public TextView title, topic, subject, teacher, time, meetingid, meetingpass;
       public Button join;
        public OnlineClassVH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.online_title);
            topic = itemView.findViewById(R.id.topic_online_class);
            subject = itemView.findViewById(R.id.online_subject);
            teacher = itemView.findViewById(R.id.online_teacher);
            time = itemView.findViewById(R.id.online_classtime);
            join = itemView.findViewById(R.id.join_class_button);
            meetingid = itemView.findViewById(R.id.online_meetingid);
            meetingpass = itemView.findViewById(R.id.online_meetingpass);
        }
    }
}
