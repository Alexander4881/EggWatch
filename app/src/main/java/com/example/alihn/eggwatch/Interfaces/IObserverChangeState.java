package com.example.alihn.eggwatch.Interfaces;

public interface IObserverChangeState<T> extends IObserver {
    public void onStateChange(EggTimeState state);
}
