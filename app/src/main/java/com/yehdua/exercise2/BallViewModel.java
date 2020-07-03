package com.yehdua.exercise2;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BallViewModel extends ViewModel {
    MutableLiveData<Float> motionX = new MutableLiveData<>(0f);
    MutableLiveData<Float> motionY = new MutableLiveData<>(0f);
    float startX = 0;
    float startY = 0;


    public void velocityFromSwipe(float stopX, float stopY) {
        final float speedScale = 5;
        motionY.setValue((stopY - startY) * speedScale);
        motionX.setValue((stopX - startX) * speedScale);
    }
}


