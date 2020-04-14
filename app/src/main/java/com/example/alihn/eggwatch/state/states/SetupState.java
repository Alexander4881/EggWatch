package com.example.alihn.eggwatch.state.states;

import android.util.Log;

import com.example.alihn.eggwatch.EggTimer;
import com.example.alihn.eggwatch.Interfaces.EggTimeState;
import com.example.alihn.eggwatch.Interfaces.IObserver;
import com.example.alihn.eggwatch.state.StateManager;

public class SetupState extends EggTimeState {

    public SetupState(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public void SetupEggTimer(int timeToCook) throws Exception {
        // create a new egg timer
        stateManager.setEggTimer(new EggTimer(timeToCook));
        // setup the new observer
        stateManager.getEggTimer().registerObserver(stateManager);


        stateManager.onCountDown(stateManager.getEggTimer().getTimer());
        // set up new state
        stateManager.setCurrentState(new StopState(stateManager));
    }

    @Override
    public void StartStopEggTimer() throws Exception {
        // throw exception
        throw new Exception("You Need to Select an Egg");

    }

    @Override
    public void ResetEggTimer() throws Exception {
        // throw exception
        throw new Exception("You Need to Select an Egg");
    }
}
