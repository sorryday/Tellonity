package com.example.fragmentuiprac2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;

public class GuideActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Drone1Fragment의 도움말 확인을 위한 클래스
        setContentView(R.layout.activity_guide);
    }
}