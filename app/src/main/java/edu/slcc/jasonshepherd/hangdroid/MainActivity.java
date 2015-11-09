package edu.slcc.jasonshepherd.hangdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSinglePlayerGame(View view) {
        //explicit intent sends a message to start an activity
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
