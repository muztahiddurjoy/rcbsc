package com.scientia.science.rcbscassignmentupload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scientia.science.rcbscassignmentupload.Datasets.FilesDataset;

import java.nio.file.Files;
import java.util.ArrayList;

public class FilesImportant extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<FilesDataset> arrayList;
    private ArrayList<String> keys;
    private DatabaseReference reference;
    private ImportantFilesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_important);
        recyclerView = findViewById(R.id.recycler_important_files);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        arrayList = new ArrayList<FilesDataset>();
        keys = new ArrayList<String>();
        adapter = new ImportantFilesAdapter(arrayList,keys,FilesImportant.this, new FilesImportant());
        reference = FirebaseDatabase.getInstance().getReference().child("files");
        recyclerView.setAdapter(adapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                keys.clear();
                for (DataSnapshot ds: snapshot.getChildren()) {
                    FilesDataset dataset = ds.getValue(FilesDataset.class);
                    if (!arrayList.contains(dataset)){
                        arrayList.add(dataset);
                        keys.add(ds.getKey());
                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}