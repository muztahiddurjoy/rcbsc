package com.scientia.science.rcbscassignmentupload;

import android.os.Bundle;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scientia.science.rcbscassignmentupload.Datasets.NoticifationDataFinal;
import com.scientia.science.rcbscassignmentupload.Datasets.NotificationDataOne;
import com.scientia.science.rcbscassignmentupload.Datasets.SignUpDataset;
import com.scientia.science.rcbscassignmentupload.Interfaces.MessagingAPIRetrofit;

public class SignupFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignupFragment() {

    }

    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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
    TextInputEditText name, cleass, roll, section, id, email, contact_num, password;
    Button submit;
    DatabaseReference reference;
    SignUpDataset dataset;
    private MessagingAPIRetrofit messagingAPIRetrofit;
    private NotificationDataOne one;
    private NoticifationDataFinal noticifationDataFinal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_signup, container, false);
        name = root.findViewById(R.id.signup_name);
        cleass = root.findViewById(R.id.signup_class);
        roll = root.findViewById(R.id.signup_roll);
        section = root.findViewById(R.id.signup_section);
        id = root.findViewById(R.id.signup_id);
        email = root.findViewById(R.id.signup_email);
        contact_num = root.findViewById(R.id.signup_number_contact);
        password = root.findViewById(R.id.signup_password);
        submit = root.findViewById(R.id.signup_button);
        reference = FirebaseDatabase.getInstance().getReference();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString();
                String emailText = email.getText().toString();
                String cleassText = cleass.getText().toString();
                String rollText = roll.getText().toString();
                String sectionText = section.getText().toString();
                String idText = id.getText().toString();
                String contactText = contact_num.getText().toString();
                String passText = password.getText().toString().trim();
                if (nameText!=null||emailText!=null||cleassText!= null||rollText!= null || sectionText!=null||idText!=null||contactText!=null||passText!=null){
                    dataset = new SignUpDataset(nameText,emailText,contactText,cleassText,rollText,idText,sectionText,passText);
                    reference.child("pendingUser").push().setValue(dataset).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Snackbar.make(v,"নতুন একাউন্ট এর জন্য রিকুয়েস্ট পাঠানো হয়েছে", BaseTransientBottomBar.LENGTH_LONG).show();
                                    sendAccountNotification();
                                    name.setText("");
                                    email.setText("");
                                    cleass.setText("");
                                    roll.setText("");
                                    section.setText("");
                                    id.setText("");
                                    contact_num.setText("");
                                    password.setText("");
                                }
                        }
                    });
                }
                else{

                }
            }
        });
        return root;
    }

    private void sendAccountNotification() {
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        messagingAPIRetrofit = retrofit.create(MessagingAPIRetrofit.class);
        one = new NotificationDataOne("New Account Request",name.getText().toString()+" Wants to open a new account!");
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