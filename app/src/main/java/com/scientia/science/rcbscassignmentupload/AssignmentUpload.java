package com.scientia.science.rcbscassignmentupload;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.scientia.science.rcbscassignmentupload.Datasets.PDFUpload;

import java.util.Date;

public class AssignmentUpload extends AppCompatActivity {
    private TextInputEditText name,subject,cleass,title,section,roll,submitted_to;
    private TextView status;
    private Button add_file;
    private StorageReference storageReference;
    private DatabaseReference dbreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_upload);
        name = findViewById(R.id.name_assignment);
        subject = findViewById(R.id.subject_assignment_upload);
        cleass = findViewById(R.id.class_assignment);
        title = findViewById(R.id.assignment_title_upload);
        section = findViewById(R.id.section_assignment_upload);
        roll = findViewById(R.id.roll_assignment);
        submitted_to = findViewById(R.id.assignment_submitted_to);
        status = findViewById(R.id.file_status);
        add_file = findViewById(R.id.add_file_assignment);

        storageReference = FirebaseStorage.getInstance().getReference();
        dbreference = FirebaseDatabase.getInstance().getReference().child("files");

        add_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPDFile();
            }
        });

    }

    private void setPDFile() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"আপনার অ্যাসাইনমেন্ট সিলেক্ট করুন"),7);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK && data!=null && data.getData() !=null){
            status.setText("অ্যাাসাইনমেন্ট যুক্ত করা হয়েছে");
            uploadPDFtoFirebase(data.getData());
        }
    }

    private void uploadPDFtoFirebase(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("আপলোড করা হচ্ছে..");
        progressDialog.show();
        String nameT = name.getText().toString().trim() + "-" + title.getText().toString();
        StorageReference reference = storageReference.child("assignments/"+nameT+".pdf");
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                while (!uri.isComplete());
                Uri url = uri.getResult();
                String nameT = name.getText().toString();
                String subjectT = subject.getText().toString();
                String cleassT = cleass.getText().toString();
                String titleT = title.getText().toString();
                String sectionT = section.getText().toString();
                String rollT = roll.getText().toString();
                String submitted_toT = submitted_to.getText().toString();
                PDFUpload pdfUpload = new PDFUpload(titleT,nameT,sectionT,rollT,submitted_toT,subjectT,cleassT, url.toString(),new Date().toLocaleString());
                if (!nameT.isEmpty() || !subjectT.isEmpty() || !cleassT.isEmpty() || !titleT.isEmpty() || !sectionT.isEmpty() || !rollT.isEmpty() || !submitted_toT.isEmpty()) {

                    dbreference.child("assignfiles").push().setValue(pdfUpload).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                name.setText("");
                                subject.setText("");
                                cleass.setText("");
                                title.setText("");
                                section.setText("");
                                roll.setText("");
                                submitted_to.setText("");
                                Toast.makeText(AssignmentUpload.this, "অ্যাাসাইনমেন্ট আপলোড করা হয়েছে", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(AssignmentUpload.this, "দয়া করে সবগুলো ঘর পূরণ করুন", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress = (100.0*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setMessage("আপলোড করা হয়েছে: "+(int)progress+"%");
            }
        }).addOnFailureListener((e)->{
                Toast.makeText(AssignmentUpload.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            });
    }
}