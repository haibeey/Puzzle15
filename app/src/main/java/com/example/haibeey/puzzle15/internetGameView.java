package com.example.haibeey.puzzle15;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Environment;
import android.preference.Preference;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class internetGameView extends SurfaceView  implements  Runnable {
    Context con;
    Canvas canvas;
    SurfaceHolder holder;
    Thread thisThread = null;
    MainGame controler;
    Paint p;
    Bitmap bitmap;
    boolean ok,finish;
    String Text="";
    GestureDetector GD;
    int moves,sample1,sample2;
    public SoundPool sp;
    int  time=3;
    SharedPreferences pref;
    SharedPreferences.Editor e;

    internetGameView(Context c){
        super(c);
    }
    public internetGameView(Context c, MainGame Mg, Bitmap bi) {
        super(c);
        this.con = c;
        holder = getHolder();
        this.controler = Mg;
        this.p = new Paint();
        this.bitmap = bi;
        moves=0;
        loadSound();
    }

    private void loadSound(){
        sp=new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        AssetManager as=con.getAssets();
        AssetFileDescriptor ds;
        try {
            ds = as.openFd("onfling.mp3");
            sample1 = sp.load(ds, 0);

            ds = as.openFd("onfinish.mp3");
            sample2=sp.load(ds,0);

        }catch (Exception e){e.printStackTrace();}


    }
    @Override
    public void run() {
        while (ok) {
            draw();
            if(finish)ok=false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String formatTime(int time){
        int min=time/60;
        int secs=time%60;
        String m=min<10?"0"+String.valueOf(min):String.valueOf(min);
        String s=secs<10?"0"+String.valueOf(secs):String.valueOf(secs);
        return m+":"+s;
    }

    private void screenShortAndsave(){
        try {
            /*Date d = new Date();
            android.text.format.DateFormat.format("yyyymmddhhmmss", d);
            String path = Environment.getExternalStorageDirectory() + "/" + d + ".jpg";
            View v = getRootView();
            v.setDrawingCacheEnabled(true);
            Bitmap bittmap = Bitmap.createBitmap(v.getDrawingCache());
            v.setDrawingCacheEnabled(false);
            File f = new File(path);
            FileOutputStream uts = new FileOutputStream(f);
            bittmap.compress(Bitmap.CompressFormat.JPEG,100,uts);
            uts.flush();
            uts.close();*/
            pref=con.getSharedPreferences("data",con.MODE_PRIVATE);
            e=pref.edit();
            int[] arr=new int[5];
            //String[] arr1=new String[5];
            arr[0]=pref.getInt("one",0);
            arr[1]=pref.getInt("two",0);
            arr[2]=pref.getInt("three",0);
            arr[3]=pref.getInt("four",0);
            arr[4]=pref.getInt("five",0);
            for(int i=0;i<5;i++){
                if(time>arr[i]){
                    int last=4;
                    while (last>i){
                        arr[last]=arr[last-1];
                        --last;
                    }
                    arr[i]=time;
                    break;
                }
            }
            e.putInt("one",arr[0]);
            e.putInt("two",arr[1]);
            e.putInt("three",arr[2]);
            e.putInt("four",arr[2]);
            e.putInt("five",arr[4]);
            e.commit();
            /*arr1[0]=pref.getString("oneone","");
            arr1[1]=pref.getString("twotwo","");
            arr1[2]=pref.getString("threethree","");
            arr1[3]=pref.getString("fourfour","");
            arr1[4]=pref.getString("fivefour","");
            int last=4;
            while(last>0){
                arr1[last]=arr1[last-1];
                --last;
            }*/

        }catch (Exception e){e.printStackTrace();}
    }

    public void draw() {
        if (holder.getSurface().isValid()) {
            canvas = holder.lockCanvas();
            time++;
            drawTopWhenDrawing();
            int v=0;
            for (int i = 0; i < this.controler.level; i++) {
                for (int j = 0; j < controler.level; j++) {
                    v++;
                    int Here=this.controler.wb[i][j];
                    if(Here==controler.level*controler.level){
                        Rect Dest = this.controler.DEST.get(v);
                        p.setColor(Color.WHITE);
                        canvas.drawRect(Dest,p);
                        continue;
                    }
                    Rect Dest = this.controler.DEST.get(v);
                    Rect src = this.controler.SRC.get(Here);
                    canvas.drawBitmap(this.bitmap, src,Dest, p);
                    p.setColor(Color.WHITE);
                    canvas.drawLine(Dest.left,Dest.top,Dest.right,Dest.top+5,p);
                    canvas.drawLine(Dest.left,Dest.bottom,Dest.right,Dest.bottom+5,p);
                    canvas.drawLine(Dest.left,Dest.top,Dest.left+5,Dest.bottom,p);
                    canvas.drawLine(Dest.right,Dest.top,Dest.right+5,Dest.bottom,p);
                }
            }
            drawBottomWhenDrawing();
            Text="";
            if(this.controler.isWin()==1){
                screenShortAndsave();
                drawOnFinish();
                finish=true;
            }
            holder.unlockCanvasAndPost(canvas);
        }
    }
    public  void drawTopWhenDrawing(){
        controler.findLast();
        // Rect r=new Rect(0,0,controler.width1,(controler.height1/controler.level)/4);
        //p.setTextSize((controler.height1/controler.level)/8);
        //p.setColor(Color.WHITE);
        //canvas.drawText(Text,0,(float)r.centerY(),p);
        //Text="";
        //Log.i("last x,last y",""+time);
    }

    private void drawOnFinish(){
        Rect R=new Rect(0,0,this.controler.width1,this.controler.height1);
        //canvas.drawColor(Color.DKGRAY);
        p.setTextSize(50);
        //p.setColor(Color.DKGRAY);
        // canvas.drawRect(R,p);
        p.setColor(Color.CYAN);
        canvas.drawText("Congratulations,",40,(float)R.centerY()-(R.centerY()/2),p);
        p.setTextSize(30);
        canvas.drawText("You Finish Arranging The Tiles,",20,(float)R.centerY()-(R.centerY()/2)+50,p);
        canvas.drawText("Back For Other Options.",20,(float)R.centerY()-(R.centerY()/2)+100,p);
        sp.play(sample2,1,1,0,0,1);

    }

    public void drawBottomWhenDrawing(){
        Rect r=new Rect(0,controler.top1,controler.width1,controler.height1);
        p.setColor(Color.BLACK);
        canvas.drawRect(r,p);
        p.setColor(Color.WHITE);
        p.setTextSize(30);
        canvas.drawText("Moves:"+String.valueOf(moves),10,r.centerY()-40,p);
        Rect rr=new Rect(controler.width1-((controler.width1/controler.level)*2),controler.top1+50,controler.width1-10,controler.height1);
        canvas.drawText("Time "+formatTime(time/2),10,r.centerY(),p);
        canvas.drawBitmap(bitmap,null,rr,p);
    }

    private void show(){
        Log.i("start","my watch as start");
        for(int i=0;i<this.controler.level;i++) {
            String t="";
            for (int j = 0; j < this.controler.level; j++)
                t+=this.controler.wb[i][j] + " ";
            Log.i("",t);
        }
        Log.i("start","my watch as end");
    }

    private class  gestureDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent ev1,MotionEvent ev2 ,float vx,float vy){
            controler.findLast();
            Log.i("last x,last y",controler.lastX+" "+controler.lastY+" ");
            float[] rlud=new float[4];
            //r=1,l=2,d=3,u=4
            rlud[0]=ev2.getX()-ev1.getX();
            rlud[1]=ev1.getX()-ev2.getX();
            rlud[2]=ev2.getY()-ev1.getY();
            rlud[3]=ev1.getY()-ev2.getY();
            int h=maxIntArr(rlud);

            if(finish)return  false;
            sp.play(sample1,1,1,0,0,1);
            if(h==1){
                //right swipe
                if(controler.lastY+1<controler.level){
                    controler.play(controler.lastX,controler.lastY,controler.lastX,controler.lastY+1);
                    controler.lastY+=1;
                    moves++;
                    return false;
                }else{
                    Text="Cant Move in That Direction";
                }
            }
            if(h==2){
                //left swipe
                if(controler.lastY-1>=0){
                    controler.play(controler.lastX,controler.lastY,controler.lastX,controler.lastY-1);
                    controler.lastY-=1;
                    moves++;
                    return false;
                }else{
                    Text="Cant Move in That Direction";
                }
            }

            if(h==3){
                //down swipe
                if(controler.lastX+1<controler.level){
                    controler.play(controler.lastX,controler.lastY,controler.lastX+1,controler.lastY);
                    controler.lastX+=1;
                    moves++;
                    return false;
                }else{
                    Text="Cant Move in That Direction";
                }
            }

            if(h==4){
                //upward swipe
                if(controler.lastX-1>=0){
                    controler.play(controler.lastX,controler.lastY,controler.lastX-1,controler.lastY);
                    controler.lastX-=1;
                    moves++;
                    return false;
                }else {
                    Text="Cant Move in That Direction";
                }
            }

            return false;
        }

        private int maxIntArr(float[] arr){
            float s=0;
            int res=0;
            for(int i=0;i<arr.length;i++){
                if(arr[i]>s){
                    s=arr[i];res=i;
                }
            }
            return res+1;
        }

        @Override
        public  boolean onDown(MotionEvent me) {
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent Me){
        return GD.onTouchEvent(Me);
    }

    public void resume() {
        ok = true;
        thisThread = new Thread(this);
        thisThread.start();
        GD=new GestureDetector(getContext(),new gestureDetector());
    }
    public void pause() {
        ok=false;
        try {
            thisThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
