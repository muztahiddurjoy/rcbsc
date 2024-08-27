package com.scientia.science.rcbscassignmentupload;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scientia.science.rcbscassignmentupload.Datasets.ContactDataset;

import java.util.ArrayList;

public class CallTeachers extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public CallTeachers() {
    }

    public static CallTeachers newInstance(String param1, String param2) {
        CallTeachers fragment = new CallTeachers();
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
    private ArrayList<ContactDataset> arrayList;
    private CallTeacherAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root  = inflater.inflate(R.layout.fragment_call_teachers, container, false);
        recyclerView = root.findViewById(R.id.recycler_call_teacher);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setReverseLayout(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        arrayList = new ArrayList<ContactDataset>();
        adapter = new CallTeacherAdapter(arrayList,getActivity());
        arrayList.add(new ContactDataset("Aminul Islam","+8801724-582013"));
        arrayList.add(new ContactDataset("Asaduzzaman Asad","+8801956-242532"));
        arrayList.add(new ContactDataset("Nasima Sultana","+8801752-364577"));
        arrayList.add(new ContactDataset("Hafizur Rahman ","+8801874-502343"));
        arrayList.add(new ContactDataset("Abul kashem","+880 1712-060933"));
        arrayList.add(new ContactDataset("Monirul Islam","+880 1912-186155"));
        arrayList.add(new ContactDataset("Mosharraf Hossain","+880 1716-902534"));
        arrayList.add(new ContactDataset("Sabera Sultana","+880 1791-088274"));
        arrayList.add(new ContactDataset("Shamim Ara","+880 1719-244400"));
        arrayList.add(new ContactDataset("Zubair Rahman ","+880 1515-227855"));
        arrayList.add(new ContactDataset("Md Shaharuzzaman","+880 1515-238175"));
        arrayList.add(new ContactDataset("Habiba Khatun ","+880 1724-950540"));
        arrayList.add(new ContactDataset("Md.Rofiqul Islam ","+880 1749-788197"));
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(arrayList.size());
        adapter.notifyDataSetChanged();
        return root;
    }
}