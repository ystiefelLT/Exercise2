package com.yehdua.exercise2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import android.widget.ImageView;

import com.yehdua.exercise2.Utils.FlingAnimationUtils;
import com.yehdua.exercise2.Utils.InPlaceAnimationUtils;
import com.yehdua.exercise2.Utils.MoveAnimationUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private BallViewModel ballViewModel;
    private InPlaceAnimationUtils inPlaceAnimationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ballViewModel = new ViewModelProvider(this).get(BallViewModel.class);
        // set onClick listeners
        initButtons();
        initTouch();

    }

    private void initButtons() {
        findViewById(R.id.bounce_button).setOnClickListener(this);
        findViewById(R.id.swap_button).setOnClickListener(this);
        findViewById(R.id.spin_button).setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ImageView ball = findViewById(R.id.ball);
        inPlaceAnimationUtils = new InPlaceAnimationUtils(ball);
        final View mainView = findViewById(R.id.ball_frame);
        final FlingAnimationUtils animationUtilities = new FlingAnimationUtils(ballViewModel, ball);
        animationUtilities.initAnimation(this);
        mainView.post(animationUtilities.initScreen(mainView));
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
                        ballViewModel.velocityFromSwipe(0, 0);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        MoveAnimationUtils.moveBall(view, motionEvent);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
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
            inPlaceAnimationUtils.startBounce();
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {

            inPlaceAnimationUtils.swapBalls();
        }
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            ballViewModel.velocityFromSwipe(velocityX, velocityY);
            return true;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bounce_button:
                inPlaceAnimationUtils.startBounce();
                break;
            case R.id.spin_button:
                inPlaceAnimationUtils.startSpin();
                break;
            case R.id.swap_button:
                inPlaceAnimationUtils.swapBalls();
                break;
        }

    }




}
