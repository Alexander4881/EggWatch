package com.example.alihn.eggwatch.state.states;

import android.util.Log;

import com.example.alihn.eggwatch.Interfaces.EggTimeState;
import com.example.alihn.eggwatch.Interfaces.IObserver;
import com.example.alihn.eggwatch.state.StateManager;

public class StartState extends EggTimeState {

    public StartState(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public void SetupEggTimer(int timeToCook) throws Exception {
        // throw exception
        throw new Exception("You need to stop the timer");
    }

    @Override
    public void StartStopEggTimer() throws Exception {
        // start the thread
        stateManager.getEggTimer().start();
        // setup the new state
        stateManager.setCurrentState(new StopState(stateManager));
    }

    @Override
    public void ResetEggTimer() throws Exception {
        // throw exception
        throw new Exception("You Need to Stop The Timer");
    }
}
