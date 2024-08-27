package com.scientia.science.rcbscassignmentupload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        FirebaseMessaging.getInstance().subscribeToTopic("student");
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_main,new Assignment()).commit();
        Assignment assignment = new Assignment();
        NoticeBoard noticeBoard = new NoticeBoard();
        HelpEmail helpEmail = new HelpEmail();
        CallTeachers callTeachers = new CallTeachers();
        OnlineClassFragment  onlineClassFragment = new OnlineClassFragment();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.assignment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_main,assignment).commit();
                        return true;
                    case R.id.notice_board:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_main,noticeBoard).commit();
                        return true;
                    case R.id.help_email:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_main,helpEmail).commit();
                        return true;
                    case R.id.call_teacher:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_main,callTeachers).commit();
                        return true;
                    case R.id.navigation_online_class:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_main,onlineClassFragment).commit();
                        return true;
                }
                return true;
            }
        });
    }
}