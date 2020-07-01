package com.yehdua.exercise2;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AnimationCalculatorTest {


    @Test
    public void velocityChecker_checkDown(){
        AnimationCalculator animationCalculator = new AnimationCalculator();
        animationCalculator.moveBall(R.id.down_btn, 1);
        assertEquals(animationCalculator.deltaY, animationCalculator.speedScale);
        animationCalculator.moveBall(R.id.down_btn, -1);
        assertEquals(animationCalculator.deltaY, 0);
    }
    @Test
    public void velocityChecker_checkUp(){
        AnimationCalculator animationCalculator = new AnimationCalculator();
        animationCalculator.moveBall(R.id.up_btn, 1);
        assertEquals(animationCalculator.deltaY, -animationCalculator.speedScale);
        animationCalculator.moveBall(R.id.up_btn, -1);
        assertEquals(animationCalculator.deltaY, 0);
    }
    @Test
    public void velocityChecker_checkRight(){
        AnimationCalculator animationCalculator = new AnimationCalculator();
        animationCalculator.moveBall(R.id.right_btn, 1);
        assertEquals(animationCalculator.deltaX, animationCalculator.speedScale);
        animationCalculator.moveBall(R.id.right_btn, -1);
        assertEquals(animationCalculator.deltaX, 0);
    }
    @Test
    public void velocityChecker_checkLeft(){
        AnimationCalculator animationCalculator = new AnimationCalculator();
        animationCalculator.moveBall(R.id.left_btn, 1);
        assertEquals(animationCalculator.deltaX, -animationCalculator.speedScale);
        animationCalculator.moveBall(R.id.left_btn, -1);
        assertEquals(animationCalculator.deltaX, 0);
    }
}
