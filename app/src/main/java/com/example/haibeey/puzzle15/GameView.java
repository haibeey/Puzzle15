package com.example.haibeey.puzzle15;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.HashMap;


public class GameView extends SurfaceView  implements  Runnable {
    Context con;
    Canvas canvas;
    SurfaceHolder holder;
    Thread thisThread = null;
    MainGame controler;
    Paint p;
    Bitmap bitmap;
    boolean ok,finish;
    GestureDetector GD;
    GestureDetector GDE;
    int moves,sample1,sample2;
    public SoundPool sp;
    long  time=System.currentTimeMillis();
    SharedPreferences pref;
    SharedPreferences.Editor e;
    boolean TouchType;
    boolean firstTime=true;


    GameView(Context c){
        super(c);
    }

    public GameView(Context c, MainGame Mg, Bitmap bi,boolean touchType) {
        super(c);
        this.con = c;
        holder = getHolder();
        this.controler = Mg;
        this.p = new Paint();
        this.bitmap = bi;
        moves=0;
        TouchType=touchType;
        loadSound();
        time=System.currentTimeMillis()/1000;
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
        }
    }

    private String formatTime(long time){
        long min=time/60;
        long secs=time%60;
        String m=min<10?"0"+String.valueOf(min):String.valueOf(min);
        String s=secs<10?"0"+String.valueOf(secs):String.valueOf(secs);
        return m+":"+s;
    }

    private synchronized void screenShortAndsave(){
        try {
            pref=con.getSharedPreferences("data",con.MODE_PRIVATE);
            e=pref.edit();
            long[] arr=new long[5];
            long lo=System.currentTimeMillis();
            long Time=lo/1000-time;
            arr[0]=pref.getLong("one",lo);
            arr[1]=pref.getLong("two",lo);
            arr[2]=pref.getLong("three",lo);
            arr[3]=pref.getLong("four",lo);
            arr[4]=pref.getLong("five",lo);
            for(int i=0;i<5;i++){
                if(Time<arr[i]){
                    int last=4;
                    while (last>i){
                        arr[last]=arr[last-1];
                        --last;
                    }
                    arr[i]=Time;
                    break;
                }
            }
            put(e,arr[0],lo,"one");
            put(e,arr[1],lo,"two");
            put(e,arr[2],lo,"three");
            put(e,arr[3],lo,"four");
            put(e,arr[4],lo,"five");
            e.commit();
        }catch (Exception e){e.printStackTrace();}
    }

    private void put(SharedPreferences.Editor e,long val,long def,String name){
        if(val==def)
            e.putLong(name,0);
        else
            e.putLong(name,val);

    }
    public void draw() {
        if (holder.getSurface().isValid()) {
            canvas = holder.lockCanvas();
            if(canvas==null)return;
            drawTopWhenDrawing();
            int v=0;
            if(!firstTime && !TouchType){
                Rect v1Dest=controler.DEST.get(controler.lastPx);
                Rect v1Src=controler.SRC.get(controler.lastPx);

                Rect v2Dest=controler.DEST.get(controler.lastPx);
                Rect v2Src=controler.SRC.get(controler.lastPx);

                canvas.drawBitmap(bitmap,v1Src,v2Dest,p);
                canvas.drawBitmap(bitmap,v2Src,v1Dest,p);

                if(controler.isWin()==1){
                    screenShortAndsave();
                    drawOnFinish();
                    finish=true;
                }

                holder.unlockCanvasAndPost(canvas);
                return;
            }

            if(!firstTime && TouchType){
                Rect v1Dest=controler.DEST.get(controler.lastPx);

                Rect v2Src=controler.SRC.get(controler.lastPx);

                canvas.drawRect(v1Dest,p);
                canvas.drawBitmap(bitmap,v2Src,v1Dest,p);

                if(controler.isWin()==1){
                    screenShortAndsave();
                    drawOnFinish();
                    finish=true;
                }

                holder.unlockCanvasAndPost(canvas);
                return;
            }

            for (int i = 0; i < controler.level ; i++) {
                for (int j = 0; j < controler.level; j++) {
                    v++;
                    int Here=controler.wb[i][j];
                    if(Here==controler.level*controler.level && TouchType){
                        Rect Dest = this.controler.DEST.get(v);
                        p.setColor(Color.WHITE);
                        canvas.drawRect(Dest,p);
                        continue;
                    }
                    Rect Dest = controler.DEST.get(v);
                    Rect src = controler.SRC.get(Here);
                    canvas.drawBitmap(bitmap, src,Dest, p);
                    p.setColor(Color.WHITE);
                    p.setStrokeWidth(3);
                    canvas.drawLine(Dest.left,Dest.top,Dest.right,Dest.top,p);
                    canvas.drawLine(Dest.left,Dest.bottom,Dest.right,Dest.bottom,p);
                    canvas.drawLine(Dest.left,Dest.top,Dest.left,Dest.bottom,p);
                    canvas.drawLine(Dest.right,Dest.top,Dest.right,Dest.bottom,p);
                }
            }
            drawBottomWhenDrawing();
            if(controler.isWin()==1){
                screenShortAndsave();
                drawOnFinish();
                finish=true;
            }
            firstTime=true;
            holder.unlockCanvasAndPost(canvas);
        }
    }
    public  void drawTopWhenDrawing(){
        controler.findLast();
    }

    private void drawOnFinish(){
        Rect R=new Rect(0,0,controler.width1,controler.height1);
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
        canvas.drawText("Time "+formatTime(System.currentTimeMillis()/1000-time),10,r.centerY(),p);
        canvas.drawBitmap(bitmap,null,rr,p);
    }

    private class  gestureDetectorOnEach extends GestureDetector.SimpleOnGestureListener{
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
            int x,y;
            int[] a=resolve(findDRect(ev1.getX(),ev1.getY()));
            x=a[0];y=a[1];
            if(finish)return  false;
            sp.play(sample1,1,1,0,0,1);
            if(h==1){
                //right swipe
                if(y+1<controler.level){
                    controler.play(x,y,x,y+1);
                    controler.lastY+=1;
                    moves++;
                    return false;
                }
            }
            if(h==2){
                //left swipe
                if(y-1>=0){
                    controler.play(x,y,x,y-1);
                    controler.lastY-=1;
                    moves++;
                    return false;
                }
            }

            if(h==3){
                //down swipe
                if(x+1<controler.level){
                    controler.play(x,y,x+1,y);
                    controler.lastX+=1;
                    moves++;
                    return false;
                }
            }

            if(h==4){
                //upward swipe
                if(x-1>=0){
                    controler.play(x,y,x-1,y);
                    controler.lastX-=1;
                    moves++;
                    return false;
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

        private Boolean findInRange(float x,float y,Rect r){
            boolean res=true;
            if(x<r.left)res=false;
            if(r.top>y)res=false;
            if(x>r.right)res=false;
            if(y>r.bottom)res=false;
            return res;
        }
        private int[] resolve(int i){
            int[] r={i/4,i%4};
            return r;
        }
        private int findDRect(float x,float y){
            for(int i=1;i<=16;i++){
                if(findInRange(x,y,controler.DEST.get(i))){
                    return i-1;
                }
            }
            return 0;
        }

        @Override
        public  boolean onDown(MotionEvent me) {
            return true;
        }
    }

    private class  gestureDetectorClassics extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onScroll(MotionEvent ev1,MotionEvent ev2,float vx,float vy){
            controler.findLast();
            float[] rlud=new float[4];
            //r=1,l=2,d=3,u=4
            rlud[0]=ev2.getX()-ev1.getX();
            rlud[1]=ev1.getX()-ev2.getX();
            rlud[2]=ev2.getY()-ev1.getY();
            rlud[3]=ev1.getY()-ev2.getY();
            int h=maxIntArr(rlud);
            int x,y;
            int[] a=resolve(findDRect(ev1.getX(),ev1.getY()));
            x=a[0];y=a[1];
            if(finish)return  true;
            if(x==controler.lastX && y==controler.lastY)return true;
            if(h==1){
                //right swipe
                if(y+1<controler.level && controler.lastY-1 >=0 && checkWhere(x,y+1,controler.lastX,controler.lastY) ){
                    sp.play(sample1,1,1,0,0,1);
                    controler.play(x,y,x,y+1);
                    controler.lastY-=1;
                    moves++;
                    return false;
                }
            }
            if(h==2){
                //left swipe
                if(y-1>=0  && controler.lastY+1<controler.level && checkWhere(x,y-1,controler.lastX,controler.lastY)){
                    sp.play(sample1,1,1,0,0,1);
                    controler.play(x,y,x,y-1);
                    controler.lastY+=1;
                    moves++;
                    return false;
                }
            }

            if(h==3){
                //down swipe
                if(x+1<controler.level && controler.lastX-1>=0 && checkWhere(x+1,y,controler.lastX,controler.lastY)) {
                    sp.play(sample1, 1, 1, 0, 0, 1);
                    controler.play(x, y, x + 1, y);
                    controler.lastX -= 1;
                    moves++;
                    return false;
                }
            }

            if(h==4){
                //upward swipe
                if(x-1>=0 && controler.lastX+1<controler.level && checkWhere(x-1,y,controler.lastX,controler.lastY)){
                    sp.play(sample1,1,1,0,0,1);
                    controler.play(x,y,x-1,y);
                    controler.lastX+=1;
                    moves++;
                    return false;
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


        private boolean checkWhere(int x,int y,int lastX,int lastY){
            if(x==lastX && y==lastY)
                return true;
            else if(x==lastX && y==lastY)
                return  true;
            else if(x==lastX && y==lastY)
                return true;
            else if(x==lastX && y==lastY)
                return true;
            else
                return false;
        }

        @Override
        public  boolean onDown(MotionEvent me) {
            return true;
        }
    }

    private boolean findInRange(float x,float y,Rect r){
        boolean res=true;
        if(x<r.left)res=false;
        if(r.top>y)res=false;
        if(x>r.right)res=false;
        if(y>r.bottom)res=false;
        return res;
    }


    private int[] resolve(int i){
        int[] r={i/4,i%4};
        return r;
    }

    private int findDRect(float x,float y){
        for(int i=1;i<=16;i++){
            if(findInRange(x,y,controler.DEST.get(i))){
                return i-1;
            }
        }
        return 0;
    }


    @Override
    public boolean onTouchEvent(MotionEvent Me){

        if(TouchType){
            return GD.onTouchEvent(Me);
        }else{
            return GDE.onTouchEvent(Me);

        }
    }

    public void resume() {
        ok = true;
        thisThread = new Thread(this);
        thisThread.start();
        GD=new GestureDetector(getContext(),new gestureDetectorClassics());
        GDE=new GestureDetector(getContext(),new gestureDetectorOnEach());
    }
    public void pause() {
        ok=false;
        try {
            thisThread.join();
        }catch (Exception e){}
    }

}
