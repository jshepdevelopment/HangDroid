package edu.slcc.jasonshepherd.hangdroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jason Shepherd on 11/9/2015.
 */
public class GameOverActivity extends AppCompatActivity {

    private int playerPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        setContentView(R.layout.activity_game_over);

        int points = getIntent().getIntExtra("PointsID", 0); //defaults to 0 if data does not come with intent
        TextView textView = (TextView) findViewById(R.id.textViewPoints); //specified from game over xml
        textView.setText(String.valueOf(points)); //all text fields are Strings... it will accept an int but will not display properly

        playerPoints = points;

    }

    //method needed to save the score
    public void saveScore(View view) { //every time you implement an onclick it needs a view for parameter

        //SET up to store preferences
        SharedPreferences preferences = getSharedPreferences("WORD_SCORES", Context.MODE_PRIVATE);
        //get name from game over XML
        EditText editText = (EditText) findViewById(R.id.editTextName);
        //set it to a string
        String name = editText.getText().toString();
        //start the preference editor
        SharedPreferences.Editor editor = preferences.edit();
        //get previous scores USING THE KEY!
        String previousScores = preferences.getString("SCORES","");
        Log.d("MYLOG", "Previous Scores: " + previousScores);

        //KEY = SCORES, VALUE = Concatinated String....
        editor.putString("SCORES", name + " " + playerPoints + " POINTS\n" + previousScores);
        //saves buffer
        editor.commit();

        //NAME X POINTS ]n NAME2 Y POINTSsd

        Toast.makeText(this, "Score Saved", Toast.LENGTH_SHORT).show();;
        editText.setText("");;
        //finish();
    }
}
