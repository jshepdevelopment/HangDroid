package edu.slcc.jasonshepherd.hangdroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Jason Shepherd on 11/9/2015.
 */
public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        setContentView(R.layout.activity_game_over);

        int points = getIntent().getIntExtra("PointsID", 0); //defaults to 0 if data does not come with intent
        TextView textView = (TextView) findViewById(R.id.textViewPoints); //specified from game over xml
        textView.setText(String.valueOf(points)); //all text fields are Strings... it will accept an int but will not display properly

    }
}
