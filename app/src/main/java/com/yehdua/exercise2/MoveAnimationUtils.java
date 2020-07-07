package com.yehdua.exercise2;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.annotation.IntDef;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class MoveAnimationUtils {

    private BallViewModel ballViewModel;
    private int screenHeight, screenWidth;
    private ImageView ball;
    private final int BALL_UNDER_SCREEN = 1;
    private final int BALL_OVER_SCREEN = -1;
    private final int BALL_ON_SCREEN = 0;

    MoveAnimationUtils(BallViewModel ballViewModel, ImageView ball) {
        this.ballViewModel = ballViewModel;
        this.ball = ball;
    }


    public Runnable initScreen(final View mainView){
        final MoveAnimationUtils moveAnimationUtils = this;
        return new Runnable() {
            @Override
            public void run() {
                int[] position = {0, 0};
                mainView.getLocationOnScreen(position);
                moveAnimationUtils.screenHeight = mainView.getMeasuredHeight() + position[1];
                moveAnimationUtils.screenWidth = mainView.getMeasuredWidth() + position[0];
            }
        };

    }

    private FlingAnimation genAnimation(
            DynamicAnimation.ViewProperty translation,
            FlingAnimation.OnAnimationUpdateListener updateListener) {
        FlingAnimation flingAnimation = new FlingAnimation(ball, translation);
        flingAnimation
                .setMinValue(-2000)
                .setMaxValue(2000)
                .setFriction(0.7f);
        ((DynamicAnimation) flingAnimation).addUpdateListener(updateListener);
        return flingAnimation;
    }


    private void setObserver(final FlingAnimation flingAnimation,
                             LifecycleOwner owner,
                             MutableLiveData<Float> liveData){
        liveData.observe(owner, new Observer<Float>() {
            @Override
            public void onChanged(Float newFling) {
                flingAnimation.setStartVelocity(newFling).start();
            }
        });
    }

    public void initAnimation(LifecycleOwner owner) {

        FlingAnimation flingAnimationX = genAnimation(
                DynamicAnimation.TRANSLATION_X, horizontalUpdate());
        setObserver(flingAnimationX, owner, ballViewModel.motionX);

        FlingAnimation flingAnimationY = genAnimation(
                DynamicAnimation.TRANSLATION_Y, verticalUpdate());
        setObserver(flingAnimationY, owner, ballViewModel.motionY);
    }


    private FlingAnimation.OnAnimationUpdateListener horizontalUpdate() {
        return new FlingAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
                int[] location = {0, 0};
                ball.getLocationOnScreen(location);
                int ballDirection = ballOffScreen(location[0], screenWidth, ball.getWidth());
                if (ballDirection != BALL_ON_SCREEN) {
                    ballViewModel.motionX.setValue(ballDirection * Math.abs(velocity));
                }
            }
        };
    }

    private FlingAnimation.OnAnimationUpdateListener verticalUpdate() {
        return new FlingAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
                int[] location = {0, 0};
                ball.getLocationOnScreen(location);
                int ballDirection = ballOffScreen(location[1], screenHeight, ball.getHeight());
                if (ballDirection != BALL_ON_SCREEN) {
                    ballViewModel.motionY.setValue(ballDirection * Math.abs(velocity));
                }
            }
        };
    }

    private int ballOffScreen(int position, int edge, int offset) {
        if (position < 0) {
            return BALL_UNDER_SCREEN;
        } else if (position + offset > edge) {
            return BALL_OVER_SCREEN;
        } else {
            return BALL_ON_SCREEN;
        }
    }





}
