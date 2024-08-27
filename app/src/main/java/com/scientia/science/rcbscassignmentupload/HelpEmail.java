package com.scientia.science.rcbscassignmentupload;

import static androidx.media.MediaBrowserServiceCompat.RESULT_OK;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.scientia.science.rcbscassignmentupload.Datasets.HelpEmailDataset;
import com.scientia.science.rcbscassignmentupload.Datasets.NoticifationDataFinal;
import com.scientia.science.rcbscassignmentupload.Datasets.NotificationDataOne;
import com.scientia.science.rcbscassignmentupload.Interfaces.MessagingAPIRetrofit;

public class HelpEmail extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public HelpEmail() {

    }
    public static HelpEmail newInstance(String param1, String param2) {
        HelpEmail fragment = new HelpEmail();
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

    private TextInputEditText email, desc;
    private Button send;
    private DatabaseReference reference;
    private HelpEmailDataset dataset;
    private MessagingAPIRetrofit messagingAPIRetrofit;
    private NotificationDataOne one;
    private NoticifationDataFinal noticifationDataFinal;
    private StorageReference storageReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_help_email, container, false);
        email = root.findViewById(R.id.email_help);
        desc = root.findViewById(R.id.desc_help);
        send = root.findViewById(R.id.send_help_btn);
        reference = FirebaseDatabase.getInstance().getReference().child("reports");
        storageReference = FirebaseStorage.getInstance().getReference().child("problems");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"ছবিটি সিলেক্ট করুন"),99);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == -1 && data.getData() != null){
            final ProgressDialog dialog = new ProgressDialog(getActivity());
            dialog.setTitle("আপলোড করা হচ্ছে...");
            storageReference.putFile(data.getData()).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    int val = (int) (snapshot.getBytesTransferred() /snapshot.getTotalByteCount() *100 );
                    dialog.setMessage(val+"%");
                }
            }).addOnSuccessListener((s)->{
                Task<Uri> task = s.getStorage().getDownloadUrl();
                while (!task.isComplete());
                Uri url = task.getResult();

                String emailT = email.getText().toString();
                String descT = desc.getText().toString();
                dataset = new HelpEmailDataset(emailT,descT, url.toString());
                reference.push().setValue(dataset).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        sendHelpNotification();
                        email.setText("");
                        desc.setText("");
                        dialog.dismiss();
                        Snackbar.make(getView(),"আমরা আপনার সমস্যা গ্রহণ করেছি। শিঘ্রই আমরা ইমেইল এর মাধ্যমে আপনাকে উত্তর দিব",BaseTransientBottomBar.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(getView(),e.getMessage(), BaseTransientBottomBar.LENGTH_LONG).show();
                    }
                });
            });
        }
    }

    private void sendHelpNotification() {
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        messagingAPIRetrofit = retrofit.create(MessagingAPIRetrofit.class);
        one = new NotificationDataOne("RCBSC Assignment Upload","একজন শিক্ষার্থী তার সমস্যা জানিয়েছে");
        noticifationDataFinal = new NoticifationDataFinal("/topics/teachers",one);
        Call<NoticifationDataFinal> res = messagingAPIRetrofit.getFcmMessage(noticifationDataFinal);
        res.enqueue(new Callback<NoticifationDataFinal>() {
            @Override
            public void onResponse(Call<NoticifationDataFinal> call, Response<NoticifationDataFinal> response) {
            }
            @Override
            public void onFailure(Call<NoticifationDataFinal> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}