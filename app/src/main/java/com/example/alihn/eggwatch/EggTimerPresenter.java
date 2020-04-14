package com.example.alihn.eggwatch;

import android.util.Log;

import com.example.alihn.eggwatch.Interfaces.EggTimeState;
import com.example.alihn.eggwatch.Interfaces.IObserverState;
import com.example.alihn.eggwatch.Interfaces.IView;
import com.example.alihn.eggwatch.state.StateManager;

public class EggTimerPresenter implements IObserverState {

    // Felts
    StateManager stateManager;
    private IView view;

    // Getters and Setters
    public IView getView() {
        return view;
    }

    public void setView(IView view) {
        this.view = view;
    }

    // constructor
    public EggTimerPresenter(IView view) {
        setView(view);
        stateManager = new StateManager();
    }

    // state connections
    public void setupEggTimer(int timeToCook) {
        try {
            stateManager.SetupEggTimer(timeToCook);
        } catch (Exception e) {
            view.onDisplayError(e);
        }
    }

    public void startStop() {
        try {
            stateManager.registerObserver(this);
            stateManager.StartStopEggTimer();
        } catch (Exception e) {
            Log.e("Exception", "setupEggTimer: " + e);
            // TODO throw exception on to the main screen through events
            view.onDisplayError(e);
        }
    }

    public void resetEggTimer(){
        try {
            stateManager.ResetEggTimer();
        } catch (Exception e) {
            view.onDisplayError(e);
        }
    }

    // events
    @Override
    public void onCountDown(int timeLeft) {
        view.onCountDown(timeLeft);
    }

    @Override
    public void onEggTimerStopped() {
        view.onEggTimerStopped();
    }

    @Override
    public void onStateChange(EggTimeState state) {
        view.onStateChange(state);
    }
}
