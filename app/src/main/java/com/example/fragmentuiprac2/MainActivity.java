package com.example.fragmentuiprac2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity
{
    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TabLayout
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Drone1"));
        tabs.addTab(tabs.newTab().setText("Drone2"));
        tabs.addTab(tabs.newTab().setText("Community"));
        tabs.setTabGravity(tabs.GRAVITY_FILL);

        //Adapter
        final ViewPager viewPager = findViewById(R.id.viewpager);
        final FragmentAdapter myPagerAdapter = new FragmentAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(myPagerAdapter);

        //탭 선택 이벤트
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
    }


    @Override
    public void onBackPressed()
    {

        // 2초간 뒤로가기를 두 번 누를경우 종료
        if (System.currentTimeMillis() > backKeyPressedTime + 1000)
        {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        if (System.currentTimeMillis() <= backKeyPressedTime + 1000)
        {
            finish();
            toast.cancel();
        }
    }
}
