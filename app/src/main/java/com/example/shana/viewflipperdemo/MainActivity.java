package com.example.shana.viewflipperdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
    private ViewFlipper vf;
    private Animation li,lo,ri,ro;
    GestureDetector gestureDetector;
    private int[]imgs ={R.drawable.nico,R.drawable.nico1,R.drawable.nico2,R.drawable.nico3,R.drawable.nico4};
    final int FLING_MIN_DISTANCE=100;//X或者y轴上移动的距离(像素)
    final int FLING_MIN_VELOCITY=200;//x或者y轴上的移动速度(像素/秒)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vf= (ViewFlipper) findViewById(R.id.photo_vf);
        gestureDetector=new GestureDetector(this,this);
        li= AnimationUtils.loadAnimation(this,R.anim.left_in);
        lo= AnimationUtils.loadAnimation(this,R.anim.left_out);
        ri= AnimationUtils.loadAnimation(this,R.anim.right_in);
        ro= AnimationUtils.loadAnimation(this,R.anim.right_out);
        for(int i=0;i<imgs.length;i++){
            ImageView img=new ImageView(this);
            img.setImageResource(imgs[i]);
            vf.addView(img,i,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        vf.setAutoStart(true);
        vf.setFlipInterval(2000);
        vf.startFlipping();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        vf.stopFlipping();
        vf.setAutoStart(false);
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {


    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Toast.makeText(this,"换",Toast.LENGTH_SHORT).show();
        if(motionEvent1.getX()-motionEvent.getX()>FLING_MIN_DISTANCE){
            vf.setInAnimation(li);
            vf.setOutAnimation(lo);
            vf.showPrevious();
            setTitle("相片编号:"+vf.getDisplayedChild());
            return true;
        }
        if(motionEvent.getX()-motionEvent1.getX()>FLING_MIN_DISTANCE){
            vf.setInAnimation(ri);
            vf.setOutAnimation(ro);
            vf.showNext();
            setTitle("相片编号:"+vf.getDisplayedChild());
            return true;
        }
        return false;
    }
}
