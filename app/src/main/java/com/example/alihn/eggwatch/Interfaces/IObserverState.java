package com.example.alihn.eggwatch.Interfaces;

public interface IObserverState extends IObserver {
    void onStateChange(EggTimeState state);
}
