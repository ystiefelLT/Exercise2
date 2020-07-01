package com.yehdua.exercise2;

public class AnimationCalculator {

    int deltaX = 0;
    int deltaY = 0;
    final int speedScale = 5;

    void moveBall(int direction, int startStop) {
        switch (direction) {
            case R.id.down_btn:
                deltaY += speedScale * startStop;
                break;
            case R.id.up_btn:
                deltaY -= speedScale * startStop;
                break;
            case R.id.right_btn:
                deltaX += speedScale * startStop;
                break;
            case R.id.left_btn:
                deltaX -= speedScale * startStop;
                break;
        }
    }
}
