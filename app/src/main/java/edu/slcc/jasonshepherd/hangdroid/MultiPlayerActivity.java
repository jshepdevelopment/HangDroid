package edu.slcc.jasonshepherd.hangdroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MultiPlayerActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);

        //connect to XML
        editText = (EditText) findViewById(R.id.editTextWord);

        //add listener
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("MYLOG", "beforeTextChanged" + " Start: " + start + " Count: " + count + " After: " + after);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("MYLOG", "onTextChange" + " Start: " + start + " Before: " + before + " Count: " + count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("MYLOG", "afterTextChanged " +s);
            }
        });

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
