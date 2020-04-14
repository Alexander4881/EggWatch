package com.example.alihn.eggwatch.Interfaces;

public interface IView {
    void onCountDown(int timeLeft);
    void onEggTimerStopped();
    void onDisplayError(Exception message);
    void onStateChange(EggTimeState state);
}
