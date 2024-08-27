package com.scientia.science.rcbscassignmentupload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationActivity extends AppCompatActivity {
private ViewPager pager;
private PagerAdapter adapter;
ViewPagerControl control = new ViewPagerControl(false);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        List<Fragment> list = new ArrayList<>();
        list.add(new SignInFragment());
        list.add(new SignupFragment());
        pager = findViewById(R.id.pager);
        adapter = new PagerAdapter(getSupportFragmentManager(),list);
        pager.setAdapter(adapter);
        if (control.change){
            pager.setCurrentItem(1);
        }
    }
    public void changePosition(int i){
        pager.setCurrentItem(i);
    }
}