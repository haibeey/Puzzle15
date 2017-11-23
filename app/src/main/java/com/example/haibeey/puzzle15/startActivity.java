package com.example.haibeey.puzzle15;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


import java.util.Random;

public class startActivity extends AppCompatActivity {
    boolean about;
    int[] imageId=new int[20];
    Handler handler;
    Thread thread;
    public SoundPool sp;
    android.support.constraint.ConstraintLayout cl;
    boolean running=true;
    int sample1,sample2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fillImageId();
        cl=(android.support.constraint.ConstraintLayout)findViewById(R.id.cl2);
        loadSound();
    }

    private void handler(){
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                //RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.rela1);
                cl.setBackgroundResource(imageId[new Random().nextInt(20)]);
            }
        };
    }

    public void classics(View v){
        Intent I=new Intent(getApplicationContext(),classics.class);
        startActivity(I);
    }

    private void fillImageId(){
        imageId[0]=R.drawable.one;
        imageId[1]=R.drawable.two;
        imageId[2]=R.drawable.three;
        imageId[3]=R.drawable.four;
        imageId[4]=R.drawable.five;
        imageId[5]=R.drawable.six;
        imageId[6]=R.drawable.seven;
        imageId[7]=R.drawable.eight;
        imageId[8]=R.drawable.nine;
        imageId[9]=R.drawable.ten;
        imageId[10]=R.drawable.eleven;
        imageId[11]=R.drawable.twelve;
        imageId[12]=R.drawable.thirteen;
        imageId[13]=R.drawable.fourteen;
        imageId[14]=R.drawable.fifteen;
        imageId[15]=R.drawable.sixteen;
        imageId[16]=R.drawable.seventeen;
        imageId[17]=R.drawable.eighteen;
        imageId[18]=R.drawable.nineteen;
        imageId[19]=R.drawable.twenty;
    }

    private void backGroungAnimation(){
        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                 while(running){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0);
                }
            }
        });
        thread.start();
    }

    public void modern(View v){
        Intent I=new Intent(getApplicationContext(),Modern.class);
        startActivity(I);
    }
    public void score(View v){
        Intent I=new Intent(getApplicationContext(),score.class);
        startActivity(I);
    }
    public void about(View v){
        about=true;
        startActivity(new Intent(this,about.class));
    }
    public void exit(View v){
        finish();
    }
    @Override
    public void onBackPressed(){
        if(!about){
            super.onBackPressed();
        }else{
            setContentView(R.layout.activity_start);
            handler();
            backGroungAnimation();
            about=false;
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        running=true;
        handler();
        backGroungAnimation();
    }



    @Override
    public void onPause(){
        super.onPause();
        running=false;
        try{
            thread.join();
        }catch (Exception e){

        }
    }

    private void loadSound(){
        sp=new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        AssetManager as=getAssets();
        AssetFileDescriptor ds;
        try {
            ds = as.openFd("onfling.mp3");
            sample1 = sp.load(ds, 0);

            ds = as.openFd("onfinish.mp3");
            sample2=sp.load(ds,0);

        }catch (Exception e){e.printStackTrace();}


    }
}