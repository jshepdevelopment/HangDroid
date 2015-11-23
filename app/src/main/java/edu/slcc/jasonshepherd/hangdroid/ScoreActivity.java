package edu.slcc.jasonshepherd.hangdroid;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //find preferences
        SharedPreferences preferences = getSharedPreferences("WORD_SCORES", MODE_PRIVATE);
        //read preferences
        String scores = preferences.getString("SCORES", "NO SCORES"); // NO SCORES if preferences not found
        //get the textview for scores
        TextView textView = (TextView) findViewById(R.id.textViewScores);
        //put scores in textview
        textView.setText(scores);

    }
}
