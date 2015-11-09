package edu.slcc.jasonshepherd.hangdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MultiPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);
    }

    public void playMultiPlayerGame(View view) {
        //create new editText object from XML
        EditText editText = (EditText) findViewById(R.id.editTextWord);
        //get word and cast word to a String
        String wordToGuess = editText.getText().toString();
        //debug
        Log.d("MYLOG", "Multi-Player Word: " + wordToGuess);
        //create intent
        Intent intent = new Intent(this, GameMultiActivity.class);
        //send word with intent
        intent.putExtra("GUESS_WORD", wordToGuess);
        //start activity
        startActivity(intent);


    }

} // end of class
