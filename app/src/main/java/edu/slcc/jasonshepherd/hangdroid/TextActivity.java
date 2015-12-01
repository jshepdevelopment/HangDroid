package edu.slcc.jasonshepherd.hangdroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TextActivity extends AppCompatActivity {

    private EditText editText;
    private SharedPreferences preferences;
    private TextView textView;
    private String textWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        //get text message from shared preferencves
        preferences = getSharedPreferences("TEXT_MSGS", MODE_PRIVATE);
        //read preferences
        getTextFromPref();
    }

    public void setTextMessage (View view) {
        getTextFromPref();
    }

    public void getTextFromPref(){
        // get text message from shared preferences
        // read preferences
        textWord = preferences.getString("TextedWord", "NO WORD"); // NOW WORD if preferences not found
        // get the textview for texted word
        if(textWord == "NO WORD") {
            textWord = "";
            Toast.makeText(this, "No Text Received", Toast.LENGTH_LONG).show();
        }
        Log.d("MYLOG", "Texted Word: " + textWord);

        // put texted word in textview
        textView = (TextView) findViewById(R.id.editTextWord);
        textView.setText(textWord);
    }

    //play button
    public void playMultiPlayerGame(View view) {
        // connect to XML
        // get word and cast word to a String
        String textWord = textView.getText().toString();
        if (textWord.length() > 0) {
            //clear field for next word
            textView.setText("");
            //clear word from shared preferences
            preferences.edit().remove("TextedWord").commit();
            Log.d("MYLOG", "MRemoved Texted Word: " + textView);
            //create intent
            Intent intent = new Intent(this, GameMultiActivity.class);
            //send word with intent
            intent.putExtra("GuessID", textWord);
            //start activity
            startActivity(intent);
        } else {
          Toast.makeText(this, "No Word Found - Try GET NEW TEXT", Toast.LENGTH_LONG).show();
        }
    } // end of play
} // end of class
