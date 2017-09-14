package com.example.haibeey.puzzle15;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int screenWidth,screenHeight;
    int screenWidth1,screenHeight1;
    Bitmap bitmap;
    GameView gv;
    Boolean startGame=false;
    SharedPreferences p;
    SharedPreferences.Editor e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int d=getIntent().getIntExtra("data",1);
        try{
        if(d==1){getCamera();}
        else if(d==2){getImageFromGallery();}
        else if(d==4){setContentView(R.layout.about_layout);
            LinearLayout ll=(LinearLayout) findViewById(R.id.abid);

            int[] arr=new int[5];
            p=getSharedPreferences("data",MODE_PRIVATE);
            arr[0]=p.getInt("one",0);
            arr[1]=p.getInt("two",0);
            arr[2]=p.getInt("three",0);
            arr[3]=p.getInt("four",0);
            arr[4]=p.getInt("five",0);
            ll.removeAllViews();
            for(int i=0;i<5;i++){
                View v= LayoutInflater.from(this).inflate(R.layout.layoutinflate,null);
                TextView t=(TextView)v.findViewById(R.id.t);
                t.setText("Time:"+String.valueOf(formatTime(arr[i])));
                ll.addView(v);
            }
        }
        else if(d==10){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.one,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==11){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.two,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==12){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.three,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==13){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.four,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==14){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.five,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==15){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.six,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==16){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.seven,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
            }
        else if(d==17){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.eight,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==18){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.nine,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==19){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.ten,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==20){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.eleven,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if (d==21){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.twelve,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==22){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.thirteen,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==23){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.fourteen,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==24){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.fifteen,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==25){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.sixteen,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==26){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.seventeen,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==27){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.eighteen,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==28){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.nineteen,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        else if(d==29){
            Display display=getWindowManager().getDefaultDisplay();
            Point p=new Point();
            display.getSize(p);
            screenHeight=p.y;screenWidth=p.x;
            int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
            bitmap=scaledBimap(R.drawable.twenty,reqH,screenWidth);
            screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
            MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
            gv=new GameView(getApplicationContext(),controlla,bitmap);
            setContentView(gv);
            gv.resume();
        }
        } catch (Exception e) {
            Log.i("error ",e.toString());
            setContentView(R.layout.activity_main);
        }
    }


    public  void start(){
        Display display=getWindowManager().getDefaultDisplay();
        Point p=new Point();
        display.getSize(p);
        screenHeight=p.y;screenWidth=p.x;
        screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
        MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
        gv=new GameView(getApplicationContext(),controlla,bitmap);
        setContentView(gv);
        gv.resume();
    }

    private int[] getImageWidthHieghtBfLoad(int id){
        int[] arr=new int[2];
        BitmapFactory.Options op=new BitmapFactory.Options();
        op.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(getResources(),id,op);
        arr[0]=op.outHeight;arr[1]=op.outWidth;
        return arr;
    }

    private  int calcInSampleSize(int id,int rh,int rw){
        int[] arr=getImageWidthHieghtBfLoad(id);
        int h=arr[0],w=arr[1];
        int ss=1;
        if(h>rh || w>rw){
            int hh=h/2,hw=w/2;

            while ((hh/ss)>=rh && (hw/ss)>=rw){
                ss*=2;
            }
        }
        return ss;
    }

    private Bitmap scaledBimap(int id,int rh,int rw){
        BitmapFactory.Options op=new BitmapFactory.Options();
        op.inSampleSize=calcInSampleSize(id,rh,rw);
        return  BitmapFactory.decodeResource(getResources(),id,op);
    }

    private String formatTime(int time){
        if (time==0)
            return "00:00";
        int min=time/60;
        int secs=time%60;
        String m=min<10?"0"+String.valueOf(min):String.valueOf(min);
        String s=secs<10?"0"+String.valueOf(secs):String.valueOf(secs);
        return m+":"+s;
    }

    private void getCamera(){
        Intent I=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(I,0);
    }

    private void getImageFromGallery(){
        Intent I=new Intent(Intent.ACTION_VIEW);
        I.setType("image/*");
        I.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(I,1);
    }

    @Override
    protected   void onActivityResult(int req,int res,Intent data){
        super.onActivityResult(req,res,data);

        if(res==0 || data==null){}
        else if(req==0){
            bitmap=(Bitmap) data.getExtras().get("data");
            startGame=true;
            start();
        }else if(req==1){
            Uri imagePath=data.getData();
            String[] location={MediaStore.Images.Media.DATA};
            Cursor cursor=getContentResolver().query(imagePath,location,null,null,null);
            cursor.moveToFirst();
            String filePath=cursor.getString(cursor.getColumnIndex(location[0]));
            bitmap=BitmapFactory.decodeFile(filePath);
            start();
            cursor.close();
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        while(true){
            if(gv==null)break;
            gv.pause();
            break;
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (gv!=null){
            gv.resume();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}


