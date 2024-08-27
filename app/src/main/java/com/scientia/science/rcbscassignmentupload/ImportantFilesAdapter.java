package com.scientia.science.rcbscassignmentupload;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scientia.science.rcbscassignmentupload.Datasets.FilesDataset;

import java.util.ArrayList;

public class ImportantFilesAdapter extends RecyclerView.Adapter<ImportantFilesAdapter.FilesVH> {
    ArrayList<FilesDataset> arrayList;
    ArrayList<String> keys;
    Context context;
    Activity activity;

    public ImportantFilesAdapter(ArrayList<FilesDataset> arrayList, ArrayList<String> keys, Context context, Activity activity) {
        this.arrayList = arrayList;
        this.keys = keys;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public FilesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FilesVH(LayoutInflater.from(context).inflate(R.layout.files_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FilesVH holder, int position) {
        FilesDataset dataset = arrayList.get(position);
        holder.title.setText(dataset.getFilename());
        holder.uploader.setText(dataset.getUploader()+" আপলোড করেছেন");
        holder.uploadtime.setText(dataset.getDatetime());
        holder.downloadfile.setOnClickListener((v)->{
            try {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(dataset.getUrl()));
                activity.startActivity(intent);
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

    public class FilesVH extends RecyclerView.ViewHolder {
        TextView title, uploader, uploadtime;
        Button downloadfile;
        public FilesVH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.file_name);
            uploader = itemView.findViewById(R.id.files_uploader);
            uploadtime = itemView.findViewById(R.id.files_uploaded_on);
            downloadfile = itemView.findViewById(R.id.download_file);
        }
    }
}
