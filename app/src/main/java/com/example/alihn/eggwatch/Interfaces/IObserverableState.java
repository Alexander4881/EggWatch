package com.example.alihn.eggwatch.Interfaces;

public interface IObserverableState<I> extends IObservable<I> {
    public void raiseOnStateChange(EggTimeState state);
}
