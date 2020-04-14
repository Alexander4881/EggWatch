package com.example.alihn.eggwatch;

import android.util.Log;

import static android.content.ContentValues.TAG;

class EggTimerRunnable implements Runnable {

    private EggTimer _eggTimer;

    public EggTimerRunnable(EggTimer eggTimer){
        _eggTimer = eggTimer;
    }

    // a simple thread that count down using getTimer var
    @Override
    public void run() {
        // check if the timer is done
        while (!_eggTimer.isTimerDone()) {
            // if the pause var is true then we sleep for 100 millis
            // it could have been done with a pulse in stead that
            // would have been better
            if (_eggTimer._pause){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                Log.e(TAG, "run: set timer = " + _eggTimer.getTimer());
                if (_eggTimer.getTimer() >= 0) {
                    try {
                        // raise event
                        _eggTimer.raiseOnCountDown(_eggTimer.getTimer());
                        // set timer
                        _eggTimer.setTimer(_eggTimer.getTimer() - 1);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    // set time to be done
                    _eggTimer.setTimerDone(true);
                    // raise event
                    _eggTimer.raiseOnEggTimerStopped();
                }
            }
        }
    }
}
