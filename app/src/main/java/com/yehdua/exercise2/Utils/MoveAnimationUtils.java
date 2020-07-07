package com.yehdua.exercise2.Utils;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MoveAnimationUtils {

    public static void moveBall(View ball, MotionEvent motionEvent){
        ball.setX(motionEvent.getRawX() - ball.getMeasuredWidth() / 2f);
        ball.setY(motionEvent.getRawY() - ball.getMeasuredHeight() / 2f - 100);
    }
}
