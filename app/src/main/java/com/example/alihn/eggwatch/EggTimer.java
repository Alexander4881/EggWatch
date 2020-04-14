package com.example.alihn.eggwatch;

import com.example.alihn.eggwatch.Interfaces.IObserver;
import com.example.alihn.eggwatch.Interfaces.IObservable;

import java.util.ArrayList;

public class EggTimer implements IObservable<IObserver> {
    private static final String TAG = "Egg Timer : ";
    // observer pattern
    private final ArrayList<IObserver> observers = new ArrayList<>();

    // Fields
    private int timer;
    private boolean timerDone = true;
    Thread eggTimer;
    boolean _pause;

    // Getter and Setters
    public int getTimer() {
        return timer;
    }
    public void setTimer(int timer) {
        this.timer = timer;
    }
    public boolean isTimerDone() {
        return timerDone;
    }
    public void setTimerDone(boolean timerDone) {
        this.timerDone = timerDone;
    }

    // Constructor
    public EggTimer(int timer) {
        this.setTimer(timer);
        this.setTimerDone(false);
    }

    // Functions
    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    // event
    @Override
    public void raiseOnCountDown(int timeLeft) {
        for (IObserver observer : observers){
            observer.onCountDown(getTimer());
        }
    }

    @Override
    public void raiseOnEggTimerStopped() {
        for (IObserver observer : observers){
            observer.onEggTimerStopped();
        }
    }

    public void start(){
        if (_pause){
            _pause = false;
        }else {
            // start a new thread of EggTimerRunnable
            this.eggTimer = new Thread(new EggTimerRunnable(this));
            // start thr thread
            this.eggTimer.start();
        }
    }

    public void stop(){
        // set bool to egg timer runnnable
        if (this.eggTimer != null)
            _pause = true;
    }
}
