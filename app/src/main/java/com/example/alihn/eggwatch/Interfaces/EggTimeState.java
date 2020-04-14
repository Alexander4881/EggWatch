package com.example.alihn.eggwatch.Interfaces;

import com.example.alihn.eggwatch.EggTimer;
import com.example.alihn.eggwatch.state.StateManager;

import java.util.Observer;

public abstract class EggTimeState implements IState {

    public StateManager stateManager;

    public EggTimeState(StateManager stateManager){
        this.stateManager = stateManager;
    }
}
