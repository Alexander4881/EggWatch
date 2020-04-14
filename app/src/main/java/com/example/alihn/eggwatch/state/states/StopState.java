package com.example.alihn.eggwatch.state.states;

import android.util.Log;

import com.example.alihn.eggwatch.Interfaces.EggTimeState;
import com.example.alihn.eggwatch.state.StateManager;

public class StopState extends EggTimeState {

    // constructor
    public StopState(StateManager stateManager){
        super(stateManager);
    }

    // interface functions
    @Override
    public void SetupEggTimer(int timeToCook) throws Exception {
        // cast exception
        throw new Exception("You need to reset the timer");
    }

    @Override
    public void StartStopEggTimer() throws Exception {
        // stop the thread
        stateManager.getEggTimer().stop();

        // set up the new state
        stateManager.setCurrentState(new StartState(stateManager));
    }

    @Override
    public void ResetEggTimer() throws Exception {
        // reset the egg timer
        stateManager.setCurrentState(new SetupState(stateManager));
    }
}
