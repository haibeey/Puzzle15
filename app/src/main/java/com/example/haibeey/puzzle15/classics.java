package com.example.haibeey.puzzle15;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class classics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        {
            getIdAndSetOnclick(R.id.camera,1);
            getIdAndSetOnclick(R.id.gallery,2);
        }

        {
            getIdAndSetOnclick(R.id.one,10);
            getIdAndSetOnclick(R.id.two,11);
            getIdAndSetOnclick(R.id.three,12);
            getIdAndSetOnclick(R.id.four,13);
            getIdAndSetOnclick(R.id.five,14);
            getIdAndSetOnclick(R.id.six,15);
            getIdAndSetOnclick(R.id.seven,16);
            getIdAndSetOnclick(R.id.eigth,17);
            getIdAndSetOnclick(R.id.nine,18);
            getIdAndSetOnclick(R.id.ten,19);
            getIdAndSetOnclick(R.id.eleven,20);
            getIdAndSetOnclick(R.id.twelve,21);
            getIdAndSetOnclick(R.id.thirteen,22);
            getIdAndSetOnclick(R.id.fourteen,23);
            getIdAndSetOnclick(R.id.fifteen,24);
            getIdAndSetOnclick(R.id.sixteen,25);
            getIdAndSetOnclick(R.id.seventeen,26);
            getIdAndSetOnclick(R.id.eighteen,27);
            getIdAndSetOnclick(R.id.nineteen,28);
            getIdAndSetOnclick(R.id.twenty,29);
        }
    }



    private void getIdAndSetOnclick(int id, final int data){
        ImageView im=(ImageView)findViewById(id);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I=new Intent(getApplicationContext(),MainActivity.class);
                I.putExtra("data",data);
                I.putExtra("type",true);
                startActivity(I);
            }
        });
    }


}
