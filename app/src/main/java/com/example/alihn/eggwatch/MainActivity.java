package com.example.alihn.eggwatch;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Presentation;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alihn.eggwatch.Interfaces.EggTimeState;
import com.example.alihn.eggwatch.Interfaces.IObserver;
import com.example.alihn.eggwatch.Interfaces.IView;

public class MainActivity extends AppCompatActivity implements IView {

    // debug
    private String TAG = "MainActivity";

    // Fields
    private EggTimerPresenter presenter;
    private Button startBTN;
    private boolean startBTNToggle;
    private ImageButton softBoiledEggBTN, mediumBoiledEggBTN, hardBoiledEggBTN;
    private TextView timerTextView,informationTextView;

    // Getter and Setters
    public Button getStartBTN() {return startBTN;}
    public void setStartBTN(Button startBTN) {this.startBTN = startBTN;}
    public TextView getTimerTextView() {return timerTextView;}
    public void setTimerTextView(TextView timerTextView) {this.timerTextView = timerTextView;}
    public ImageButton getSoftBoiledEggBTN() {return softBoiledEggBTN;}
    public void setSoftBoiledEggBTN(ImageButton softBoiledEggBTN) {this.softBoiledEggBTN = softBoiledEggBTN;}
    public ImageButton getMediumBoiledEggBTN() {return mediumBoiledEggBTN;}
    public void setMediumBoiledEggBTN(ImageButton mediumBoiledEggBTN) {this.mediumBoiledEggBTN = mediumBoiledEggBTN;}
    public ImageButton getHardBoiledEggBTN() {return hardBoiledEggBTN;}
    public void setHardBoiledEggBTN(ImageButton hardBoiledEggBTN) {this.hardBoiledEggBTN = hardBoiledEggBTN;}
    public EggTimerPresenter getPresenter() {return presenter;}
    public void setPresenter(EggTimerPresenter presenter) {this.presenter = presenter;}
    public boolean getStartBTNToggle() {
        return startBTNToggle;
    }
    public void setStartBTNToggle(boolean startBTNToggle) {
        this.startBTNToggle = startBTNToggle;
    }
    public TextView getInformationTextView() {
        return informationTextView;
    }
    public void setInformationTextView(TextView informationTextView) {
        this.informationTextView = informationTextView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //will hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //hide the title bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        // set Timer TextView
        setTimerTextView((TextView) findViewById(R.id.TimerTextView));
        setInformationTextView((TextView) findViewById(R.id.informationTextView));
        // set up buttons
        setStartBTN((Button)findViewById(R.id.StartTimerBTN));
        setSoftBoiledEggBTN((ImageButton)findViewById(R.id.EggSoftBTN));
        setMediumBoiledEggBTN((ImageButton)findViewById(R.id.EggMediumBTN));
        setHardBoiledEggBTN((ImageButton)findViewById(R.id.EggHardBTN));

        // set Presenter
        setPresenter(new EggTimerPresenter(this));

        // setup click listener
        startBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // run stop in presenter
                presenter.startStop();
            }
        });
    }

    // Method 1
    // method to chose an egg
    public void chooseEgg(View view){
        switch (view.getId()){
            case R.id.EggSoftBTN:
                // run set up
                getPresenter().setupEggTimer(61);
                // set text view
                setUpEggTimer(61);
                break;

            case R.id.EggMediumBTN:
                // run set up
                getPresenter().setupEggTimer(420);
                // set text view
                setUpEggTimer(420);
                break;

            case R.id.EggHardBTN:
                // run set up
                getPresenter().setupEggTimer(600);
                // set text view
                setUpEggTimer(600);
                break;

            default:
                throw new RuntimeException("Unknown button ID");
        }
    }

    // button click function
    public void onResetBTNClick(View view){
        presenter.resetEggTimer();
        timerTextView.setText("00:00");
    }

    // Set up the egg timer
    private void setUpEggTimer(int timeLeft){
        setTimerTextView(timeLeft);
    }

    // set the timer text view
    private void setTimerTextView(int timeLeft){
        getTimerTextView().setText(String.format("%02d:%02d", timeLeft / 60, timeLeft - ((timeLeft / 60)*60) ));
    }


    // event
    @Override
    public void onCountDown(final int timeLeft) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setTimerTextView(timeLeft);
            }
        });
    }

    // event
    @Override
    public void onEggTimerStopped() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"You're Egg's are Done!!!",Toast.LENGTH_LONG).show();
            }
        });
    }

    // event
    @Override
    public void onDisplayError(Exception message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // Add the buttons
        builder.setMessage(message.toString());
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                dialog.dismiss();
            }
        });
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // event
    @Override
    public void onStateChange(EggTimeState state) {
        Log.i(TAG, state.getClass().getCanonicalName());
        switch (state.getClass().getCanonicalName()){
            case "com.example.alihn.eggwatch.state.states.StopState":
                getInformationTextView().setText("Press Stop to Reset");
                startBTN.setText(R.string.stopBTN);
                break;
            case "com.example.alihn.eggwatch.state.states.StartState":
                getInformationTextView().setText("Press Start Timer");
                startBTN.setText(R.string.startBTN);
                break;
            case "com.example.alihn.eggwatch.state.states.StartupState":
                getInformationTextView().setText("Choose Your Egg");
                startBTN.setText(R.string.startBTN);
                break;

            default:
                break;
        }
    }
}
