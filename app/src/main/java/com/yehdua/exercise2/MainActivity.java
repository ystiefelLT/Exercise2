package com.yehdua.exercise2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity{


    private BallViewModel ballViewModel;
    private Animation bounceAnimation;
    private int screenHeight, screenWidth;
    private ImageView ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ballViewModel = new ViewModelProvider(this).get(BallViewModel.class);
        bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_animation);
        // set onClick listeners
        initTouch();

    }

    @Override
    public void onResume() {
        super.onResume();
        ball = findViewById(R.id.ball);
        final View mainView = findViewById(R.id.main_view);
        final AnimationUtilities animationUtilities = new AnimationUtilities(ballViewModel, ball);
        animationUtilities.initAnimation(this);
        mainView.post(new Runnable() {
            // use a runnable to prevent the getMeasured from returning zero
            @Override
            public void run() {
                int[] position = {0, 0};
                mainView.getLocationOnScreen(position);
                screenHeight = mainView.getMeasuredHeight() + position[1];
                screenWidth = mainView.getMeasuredWidth() + position[0];
                animationUtilities.setScreen(screenHeight, screenWidth);
            }
        });
    }



    public void onPause() {
        super.onPause();
    }

    private void initTouch() {
        final GestureDetector gestureDetector = new GestureDetector(this, new BallGestureListener());
        findViewById(R.id.ball).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ballViewModel.startX = motionEvent.getX();
                        ballViewModel.startY = motionEvent.getY();
                        break;

                    case MotionEvent.ACTION_UP:
                        ballViewModel.velocityFromSwipe(motionEvent.getX(), motionEvent.getY());
                        view.performClick();
                        break;
                }
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

    }

    private class BallGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
                ball.startAnimation(bounceAnimation);
            return true;
        }
    }

}
