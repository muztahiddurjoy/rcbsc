 package com.scientia.science.rcbscassignmentupload;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scientia.science.rcbscassignmentupload.Datasets.AssignmentDataset;

import java.util.ArrayList;

public class Assignment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Assignment() {
        // Required empty public constructor
    }

    public static Assignment newInstance(String param1, String param2) {
        Assignment fragment = new Assignment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView recyclerView;
    private ArrayList<AssignmentDataset> arrayList;
    private FirebaseRecyclerOptions<AssignmentDataset> options;
    private FirebaseRecyclerAdapter<AssignmentDataset,AssignmentViewHolder> adapter;
    private DatabaseReference reference;
    private ExtendedFloatingActionButton fab, files;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_assignment, container, false);
        recyclerView = root.findViewById(R.id.recycler_assignment);
        fab = root.findViewById(R.id.assignement_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AssignmentUpload.class));
            }
        });

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        arrayList = new ArrayList<AssignmentDataset>();
        reference = FirebaseDatabase.getInstance().getReference().child("assignments");
        options = new FirebaseRecyclerOptions.Builder<AssignmentDataset>().setQuery(reference,AssignmentDataset.class).build();
        adapter = new FirebaseRecyclerAdapter<AssignmentDataset, AssignmentViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position, @NonNull AssignmentDataset model) {
                holder.title.setText(model.getTitle());
                holder.upload.setText("আপলোড করা হয়েছে: "+model.getUploaddate());
                holder.submit.setText(model.getSubmitdate());
                holder.cleass.setText(model.getCleass());
                holder.subject.setText(model.getSubject());
            }
            @NonNull
            @Override
            public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new AssignmentViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.assignment_wor,parent,false));
            }
        };
        recyclerView.smoothScrollToPosition(0);
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}