
package com.example.fragmentuiprac2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashActivity extends Activity
{

    public void onAttachedToWindow()
    {   
        //인트로 화면설정
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    Thread splashTread;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        //splashactivity와 연결
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashactivity);
        StartAnimations();
    }

    private void StartAnimations()
    {
        // 애니메이션 효과
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        ImageView ivLogo = (ImageView) findViewById(R.id.logo);
        iv.clearAnimation();
        iv.startAnimation(anim);
        splashTread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500)
                    {
                        sleep(100);
                        waited += 100;
                    }
                    //MainActivity로 화면전환
                    Intent intent = new Intent(SplashActivity.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashActivity.this.finish();

                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        splashTread.start();
    }
    @Override
    public void onBackPressed() {
    }
}