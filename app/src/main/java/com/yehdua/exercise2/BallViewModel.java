package com.yehdua.exercise2;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BallViewModel extends ViewModel {
    public MutableLiveData<Float> motionX = new MutableLiveData<>(0f);
    public MutableLiveData<Float> motionY = new MutableLiveData<>(0f);

    void velocityFromSwipe(float vx, float vy){
        motionY.setValue(vy);
        motionX.setValue(vx);
    }
}


