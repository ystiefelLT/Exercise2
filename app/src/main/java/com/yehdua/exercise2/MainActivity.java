package com.yehdua.exercise2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {


    private Runnable runnable;
    private AnimationCalculator animationCalculator = new AnimationCalculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set onClick listeners

        findViewById(R.id.down_btn).setOnTouchListener(this);
        findViewById(R.id.up_btn).setOnTouchListener(this);
        findViewById(R.id.right_btn).setOnTouchListener(this);
        findViewById(R.id.left_btn).setOnTouchListener(this);

        initRunnable();
    }
    @Override
    public void onResume(){
        super.onResume();
        findViewById(R.id.ball).post(runnable);
    }

    private void initRunnable(){
        final View ball = findViewById(R.id.ball);
        runnable = new Runnable() {
            @Override
            public void run() {
                float y = ball.getTranslationY() + animationCalculator.deltaY;
                float x = ball.getTranslationX() + animationCalculator.deltaX;
                ball.setTranslationY(y);
                ball.setTranslationX(x);
                ball.postDelayed(this, 16); // 60fps
            }
        };
    }

    public void onPause(){
        super.onPause();
        findViewById(R.id.ball).removeCallbacks(runnable);
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                animationCalculator.moveBall(view.getId(), 1);
                break;
            case MotionEvent.ACTION_UP:
                animationCalculator.moveBall(view.getId(), -1);
                view.performClick();
                break;
            default:
                break;
        }
        return true;
    }

}
