package com.example.adam.rss;
/**
 * Created by Adam on 08/03/2018.
 *
 * Student ID:S1436108
 * Adam Hardie
 */
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SplashScreen extends AppCompatActivity {

    RelativeLayout myLayout;
    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        myLayout = (RelativeLayout) findViewById(R.id.splash);

        animationDrawable = (AnimationDrawable) myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3);
        animationDrawable.setExitFadeDuration(3);
        animationDrawable.start();

        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(1500);
                    Intent activity = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(activity);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
