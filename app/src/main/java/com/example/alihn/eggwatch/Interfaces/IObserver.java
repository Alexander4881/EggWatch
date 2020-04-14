package com.example.alihn.eggwatch.Interfaces;

public interface IObserver {
    void onCountDown(int timeLeft);
    void onEggTimerStopped();
}
