package com.example.haibeey.puzzle15;

import android.graphics.Rect;
import android.util.Log;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by haibeey on 8/11/2017.
 */

public class MainGame {

    public int[][] wb;
    int level;
    int height1;
    int  width1;
    int height2;
    int width2;
    int left1=0,top1=0,right1=0,bottom1=0,left2=0,top2=0,right2=0,bottom2=0;
    int divider;
    int lastX=0,lastY=0;
    HashMap<Integer,Rect> SRC=new HashMap<>();
    HashMap<Integer,Rect> DEST=new HashMap<>();
    MainGame(int level,int height1,int width1,int height2,int width2){
        this.level=level;
        this.wb=new int[level][level];
        this.height1=height1;
        this.width1=width1;
        this.height2=height2;
        this.width2=width2;
        this.divider=(height1-(this.height1/level)-((this.height1/level)/2))/level;
        this.top1=this.height1/level/level;
        this.bottom1=this.top1+this.divider;this.bottom2=height2/level;
        this.right1=width1/level;this.right2=width2/level;
        fill();
        scatter(level);
        swapwb(3,3,3,2);
        findLast();
    }

    private boolean canMove(int x,int y,int i,int j){return x+i>=0 && x+i<level && y+j>=0 && y+j<level;}
    private void scatter(int difficulty){
        while (this.isWin()==1) {
            for(int i=0;i<this.level;i++) {
                Random RR = new Random();
                int till = difficulty * 200;
                int sx = this.level-1, sy = this.level-1;
                while (till > 0) {
                    till--;
                    int here1,here2;
                    while((here1 = (RR.nextInt(10000)+1)%2)==(here2 = (RR.nextInt(10000)+1)%2)){}
                    if(RR.nextBoolean())
                        here1=~here1+1;
                    else{here2=~here2+1;}
                    if (canMove(sx, sy,here1, here2)) {
                        swapwb(sx,sy,here1+sx,here2+sy);
                        sx += here1;
                        sy += here2;
                    }
                 }
            }
        }
        for(int i=0;i<this.level;i++){
            for(int j=0;j<this.level;j++){
                //Log.i(" stop  ",""+this.wb[this.lastX][lastY]+" ");
                findLast();
                scatterDfs(this.lastX,this.lastY,0);
            }
        }
        findLast();
        for(int i=this.lastX;i<this.level-1;i++)
            swapwb(i,this.lastY,i+1,this.lastY);
        findLast();
        for(int i=this.lastY;i<this.level-1;i++)
            swapwb(this.lastX,i,this.lastX,i+1);
    }

    private void fill(){
        int v=0;
        for(int i=0;i<this.level;i++) {
            for (int j = 0; j < this.level; j++) {
                v++;
                DEST.put(v,new Rect(this.left1,this.top1,this.right1,this.bottom1));
                SRC.put(v,new Rect(this.left2,this.top2,this.right2,this.bottom2));

                wb[i][j]=v;

                this.left1 += this.width1 / this.level;
                this.right1 += this.width1 / this.level;
                this.left2 += this.width2 / this.level;
                this.right2 += this.width2 / this.level;
            }
            this.top1+=this.divider;this.top2+=this.height2/this.level;
            this.bottom1+=this.divider;this.bottom2+=this.height2/this.level;
            this.left2=0;this.left1=0;this.right1=this.width1/this.level;this.right2=this.width2/this.level;
        }
    }

    public void findLast(){
        for(int i=0;i<this.level;i++){
            for(int j=0;j<this.level;j++){
                if(this.wb[i][j]==this.level*this.level){
                    this.lastX=i;this.lastY=j;
                    return;
                }
            }
        }
    }



    private  void scatterDfs(int i,int j,int threshold){
        if(threshold>=this.level*this.level)return;
        Random F=new Random();
        int[] dx={1,0,-1,0};
        int [] dy={0,1,0,-1};

        for(int k=0;i<4;i++){
            int h=F.nextInt(4);
            if(canMove(i,j,dx[h],dy[h])){
                swapwb(i,j,i+dx[h],j+dy[h]);
                scatterDfs(i+dx[h],j+dy[h],threshold+1);
                return;
            }
        }
    }
    private void show(){
        for(int i=0;i<this.level;i++){
            for(int j=0;j<this.level;j++){
                Log.i("i pray",this.wb[i][j]+" "+this.wb[i][j]+" "+this.wb[i][j]+" "+this.wb[i][j]+" "+this.wb[i][j]);
            }
        }
    }

    public boolean play(int x1,int y1,int x2,int y2){
        swapwb(x1,y1,x2,y2);
        return true;
    }
    private void swapwb(int x1,int y1,int x2,int y2){
        if(x1<0 || x1>=level || x2<0 || x2>=level || y1<0 || y1>=level || y2<0 || y2>=level)return;
        int temp=wb[x1][y1];
        wb[x1][y1]=wb[x2][y2];
        wb[x2][y2]=temp;
    }
    public int isWin(){
        int v=0,val=0;
        for (int i=0;i<this.level;i++){
            for(int j=0;j<this.level;j++) {
                v++;
                if (this.wb[i][j] == v) val++;
            }
        }
        return val==this.level*this.level?1:0;
    }
}
