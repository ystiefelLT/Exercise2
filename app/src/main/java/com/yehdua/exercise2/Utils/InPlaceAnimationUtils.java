package com.yehdua.exercise2.Utils;

import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.yehdua.exercise2.R;

public class InPlaceAnimationUtils {

    private ImageView ball;
    private Animation bounceAnimation;
    private Animation spinAnimation;

    public InPlaceAnimationUtils(ImageView ball){
        this.ball = ball;
        bounceAnimation = AnimationUtils.loadAnimation(ball.getContext(), R.anim.bounce_animation);
        spinAnimation = AnimationUtils.loadAnimation(ball.getContext(), R.anim.rotation_animation);
    }

    public void swapBalls() {
        Animation fadeOutAnimation = new AlphaAnimation(1f, 0f);
        Animation fadeInAnimation = new AlphaAnimation(0f, 1f);
        fadeOutAnimation.setDuration(500);
        fadeInAnimation.setDuration(500);
        fadeOutAnimation.setAnimationListener(genAnimationListener(fadeInAnimation));
        ball.startAnimation(fadeOutAnimation);
//        ball.animate().alpha(0).setDuration().withEndAction().

    }

    public void startBounce(){
        ball.startAnimation(bounceAnimation);
    }

    public void startSpin(){
        ball.startAnimation(spinAnimation);
    }

    private Animation.AnimationListener genAnimationListener(final Animation fadeInAnimation) {
        return new Animation.AnimationListener() {
            int image;
            int size;
            int tag;

            @Override
            public void onAnimationStart(Animation animation) {
                if ((ball.getTag()).equals(ball.getContext().getResources().getString(R.string.nike_ball))) {
                    image = R.drawable.mikasa_ball;
                    size = R.dimen.mikasa_size;
                    tag = R.string.mikasa_ball;
                } else {
                    image = R.drawable.nike_ball;
                    size = R.dimen.nike_size;
                    tag = R.string.nike_ball;
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ball.setTag(ball.getContext().getResources().getString(tag));
                ball.setImageResource(image);
                ball.getLayoutParams().height = (int) ball.getContext().getResources().getDimension(size);
                ball.getLayoutParams().width = (int) ball.getContext().getResources().getDimension(size);
                ball.requestLayout();
                ball.startAnimation(fadeInAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };


    }
}
