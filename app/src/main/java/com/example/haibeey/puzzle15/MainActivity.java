package com.example.haibeey.puzzle15;

import android.content.Context;
import android.content.DialogInterface;
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
    boolean which=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int d=getIntent().getIntExtra("data",1);
        which=getIntent().getBooleanExtra("type",true);
        try{
            if(d==1){getCamera();}
            else if(d==2){getImageFromGallery();}
            else if(d==4){startActivity(new Intent(this,about.class));}
            else if(d==10){
                startGame(R.drawable.one);
            }
            else if(d==11){
                startGame(R.drawable.two);
            }
            else if(d==12){
                startGame(R.drawable.three);
            }
            else if(d==13){
                startGame(R.drawable.four);
            }
            else if(d==14){
                startGame(R.drawable.five);
            }
            else if(d==15){
                startGame(R.drawable.six);
            }
            else if(d==16){
                startGame(R.drawable.seven);
            }
            else if(d==17){
                startGame(R.drawable.eight);
            }
            else if(d==18){
                startGame(R.drawable.nine);
            }
            else if(d==19){
                startGame(R.drawable.ten);
            }
            else if(d==20){
                startGame(R.drawable.eleven);
            }
            else if (d==21){
                startGame(R.drawable.twelve);
            }
            else if(d==22){
                startGame(R.drawable.thirteen);
            }
            else if(d==23){
                startGame(R.drawable.fourteen);
            }
            else if(d==24){
                startGame(R.drawable.fifteen);
            }
            else if(d==25){
                startGame(R.drawable.sixteen);
            }
            else if(d==26){
                startGame(R.drawable.seventeen);
            }
            else if(d==27){
                startGame(R.drawable.eighteen);
            }
            else if(d==28){
                startGame(R.drawable.nineteen);
            }
            else if(d==29){
                startGame(R.drawable.twenty);
            }
        } catch (Exception e) {
            Log.i("errorerror",e.toString());
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
        GameView gv=new GameView(getApplicationContext(),controlla,bitmap,which);
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
    private void startGame(int id){
        Display display=getWindowManager().getDefaultDisplay();
        Point p=new Point();
        display.getSize(p);
        screenHeight=p.y;screenWidth=p.x;
        int reqH=(screenHeight-(screenHeight/4)-((screenHeight/4)/2));
        bitmap=scaledBimap(id,reqH,screenWidth);
        screenWidth1=bitmap.getWidth();screenHeight1=bitmap.getHeight();
        MainGame controlla=new MainGame(4,screenHeight,screenWidth,screenHeight1,screenWidth1);
        gv=new GameView(getApplicationContext(),controlla,bitmap,which);
        setContentView(gv);
    }


    private void getCamera(){
        Intent I=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(I,0);
    }

    private void getImageFromGallery(){
        Intent I=new Intent(Intent.CATEGORY_APP_GALLERY);
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
    public void onResume(){
        super.onResume();
        if (gv!=null) {
            gv.resume();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        if(gv!=null)
            gv.pause();
    }

}


