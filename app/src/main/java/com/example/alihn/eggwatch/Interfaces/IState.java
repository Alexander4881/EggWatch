package com.example.alihn.eggwatch.Interfaces;

public interface IState {
    void SetupEggTimer(int timeToCook) throws Exception;
    void StartStopEggTimer() throws Exception;
    void ResetEggTimer() throws Exception;
}
