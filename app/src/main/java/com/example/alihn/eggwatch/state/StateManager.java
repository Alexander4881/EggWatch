package com.example.alihn.eggwatch.state;

import com.example.alihn.eggwatch.EggTimer;
import com.example.alihn.eggwatch.Interfaces.EggTimeState;
import com.example.alihn.eggwatch.Interfaces.IObserver;
import com.example.alihn.eggwatch.Interfaces.IObserverChangeState;
import com.example.alihn.eggwatch.Interfaces.IObserverState;
import com.example.alihn.eggwatch.Interfaces.IObserverableState;
import com.example.alihn.eggwatch.Interfaces.IState;
import com.example.alihn.eggwatch.state.states.SetupState;

import java.util.ArrayList;

public class StateManager implements IObserverableState<IObserverState>, IState, IObserverChangeState {

    // fields
    private final ArrayList<IObserverState> observers = new ArrayList<>();
    private EggTimeState currentState;
    private EggTimer _eggTimer;

    // getter and setters
    public EggTimeState getCurrentState() {
        return currentState;
    }
    public void setCurrentState(EggTimeState currentState) {
        this.currentState = currentState;
        raiseOnStateChange(currentState);
    }
    public EggTimer getEggTimer() {
        return _eggTimer;
    }
    public void setEggTimer(EggTimer _eggTimer) {
        this._eggTimer = _eggTimer;
    }

    // constructor
    public StateManager() {
        currentState = new SetupState(this);
    }

    // observer pattern
    @Override
    public void registerObserver(IObserverState observer) throws Exception {
        observers.add(observer);
    }

    // observer pattern
    @Override
    public void removeObserver(IObserverState observer) throws Exception {
        observers.remove(observer);
    }

    // raise event
    @Override
    public void raiseOnCountDown(int timeLeft) {
        for (IObserver observer : observers) {
            observer.onCountDown(timeLeft);
        }
    }

    // raise event
    @Override
    public void raiseOnEggTimerStopped() {
        for (IObserver observer : observers) {
            observer.onEggTimerStopped();
        }
    }

    // State Functions
    @Override
    public void SetupEggTimer(int timeToCook) throws Exception {
        currentState.SetupEggTimer(timeToCook);
    }

    @Override
    public void StartStopEggTimer() throws Exception {
        currentState.StartStopEggTimer();
    }

    @Override
    public void ResetEggTimer() throws Exception {
        currentState.ResetEggTimer();
    }

    // events
    @Override
    public void onCountDown(int timeLeft) {
        raiseOnCountDown(timeLeft);
    }

    @Override
    public void onEggTimerStopped() {
        raiseOnEggTimerStopped();
    }

    @Override
    public void onStateChange(EggTimeState state) {
        raiseOnStateChange(state);
    }

    @Override
    public void raiseOnStateChange(EggTimeState state) {
        for (IObserverState observer : observers) {
            observer.onStateChange(state);
        }
    }
}
