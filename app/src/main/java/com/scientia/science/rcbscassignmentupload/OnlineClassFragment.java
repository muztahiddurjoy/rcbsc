package com.scientia.science.rcbscassignmentupload;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scientia.science.rcbscassignmentupload.Datasets.OnlineClassDataset;

import java.util.ArrayList;

public class OnlineClassFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public OnlineClassFragment() {
        // Required empty public constructor
    }


    public static OnlineClassFragment newInstance(String param1, String param2) {
        OnlineClassFragment fragment = new OnlineClassFragment();
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
    private ArrayList<OnlineClassDataset> arrayList;
    private ArrayList<String> keys;
    private DatabaseReference reference;
    private OnlineClassAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_online_class, container, false);
        recyclerView = root.findViewById(R.id.recycler_online_class);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        arrayList = new ArrayList<OnlineClassDataset>();
        keys = new ArrayList<String>();
        adapter = new OnlineClassAdapter(arrayList,keys,getActivity());
        reference = FirebaseDatabase.getInstance().getReference().child("onlineclass");
        recyclerView.setAdapter(adapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                keys.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    OnlineClassDataset dataset = ds.getValue(OnlineClassDataset.class);
                    if (!arrayList.contains(dataset)) {
                        arrayList.add(dataset);
                        keys.add(ds.getKey());
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "একটি সমস্যা হয়েছে", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}