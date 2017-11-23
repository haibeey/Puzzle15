package com.example.haibeey.puzzle15;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fill();

    }

    private void fill(){
        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        Long[] arr=new Long[5];
        long res=0;
        arr[0]=sharedPreferences.getLong("one",0);
        res+=arr[0];
        arr[1]=sharedPreferences.getLong("two",0);
        res+=arr[1];
        arr[2]=sharedPreferences.getLong("three",0);
        res+=arr[2];
        arr[3]=sharedPreferences.getLong("four",0);
        res+=arr[3];
        arr[4]=sharedPreferences.getLong("five",0);
        res+=arr[4];
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.lls);
        if(res==0){
            View view= LayoutInflater.from(this).inflate(R.layout.card,linearLayout,false);
            TextView tv=(TextView)view.findViewById(R.id.ctv);
            tv.setText("you have not complete any game");
            linearLayout.addView(view);
        }
        for(int i=0 ;i<5;i++){
            if(arr[i]!=0){
                View view= LayoutInflater.from(this).inflate(R.layout.card,linearLayout,false);
                TextView tv=(TextView)view.findViewById(R.id.ctv);
                tv.setText(formatTime(arr[i]));
                linearLayout.addView(view);
            }
        }

    }

    private String formatTime(long time){
        long min=time/60;
        long secs=time%60;
        String m=min<10?"0"+String.valueOf(min):String.valueOf(min);
        String s=secs<10?"0"+String.valueOf(secs):String.valueOf(secs);
        return m+":"+s;
    }

}
