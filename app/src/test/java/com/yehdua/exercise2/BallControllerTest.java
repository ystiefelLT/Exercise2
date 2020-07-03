package com.yehdua.exercise2;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BallControllerTest {


    @Test
    public void velocityChecker_checkDown(){
        BallController ballController = new BallController();
        ballController.moveBall(R.id.down_btn, 1);
        assertEquals(ballController.deltaY, ballController.speedScale);
        ballController.moveBall(R.id.down_btn, -1);
        assertEquals(ballController.deltaY, 0);
    }
    @Test
    public void velocityChecker_checkUp(){
        BallController ballController = new BallController();
        ballController.moveBall(R.id.up_btn, 1);
        assertEquals(ballController.deltaY, -ballController.speedScale);
        ballController.moveBall(R.id.up_btn, -1);
        assertEquals(ballController.deltaY, 0);
    }
    @Test
    public void velocityChecker_checkRight(){
        BallController ballController = new BallController();
        ballController.moveBall(R.id.right_btn, 1);
        assertEquals(ballController.deltaX, ballController.speedScale);
        ballController.moveBall(R.id.right_btn, -1);
        assertEquals(ballController.deltaX, 0);
    }
    @Test
    public void velocityChecker_checkLeft(){
        BallController ballController = new BallController();
        ballController.moveBall(R.id.left_btn, 1);
        assertEquals(ballController.deltaX, -ballController.speedScale);
        ballController.moveBall(R.id.left_btn, -1);
        assertEquals(ballController.deltaX, 0);
    }
}
