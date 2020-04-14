package com.example.alihn.eggwatch.Interfaces;

public interface IObservable<T> {
    void registerObserver(T observer) throws Exception;
    void removeObserver(T observer) throws Exception;
    void raiseOnCountDown(int timeLeft) throws Exception;
    void raiseOnEggTimerStopped() throws Exception;
}
