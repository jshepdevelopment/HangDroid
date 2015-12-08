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
    private String friendPhone;
    private String texterPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        //get text message from shared preferencves
        preferences = getSharedPreferences("TEXT_MSGS", MODE_PRIVATE);
        //read preferences to get friends phone if called from contacts activity
        friendPhone = getIntent().getStringExtra("Phone"); //defaults if data does not come with intent

        Log.d("MYLOG", "Friend: " + friendPhone);

        getTextFromPref();
        //get phone from intent if it was called from contacts
    }

    public void setTextMessage (View view) {
        getTextFromPref();
    }

    public void getTextFromPref() {
        // get text message from shared preferences
        // read preferences
        textWord = preferences.getString("TextedWord", "NO WORD"); // NOW WORD if preferences not found
        texterPhone = preferences.getString("TexterPhone", "NO PHONE"); //NO PHONE if preferences are not found
        textView = (TextView) findViewById(R.id.editTextWord);

        //set up boolean values
        boolean phone = true;
        boolean word = true;
        boolean friend = true;
        if (textWord == "NO WORD") word = false;
        if (texterPhone == "NO PHONE") phone = false;
        if (friendPhone == null) friend = false;

        // word but no friend selected
        if (!friend && word) {
            textView.setText(textWord);
            textWord = "";
            texterPhone = "";
            return;
        }

        //word and friend phone then check phone
        if (word && phone) {
            if (friendPhone.equals(texterPhone)) {
                textView.setText(textWord);
                textWord = "";
                texterPhone = "";
            } else {
                Toast.makeText(this, "No Text from Selected Friend", Toast.LENGTH_LONG).show();
            }
            return;
        }
        if (!word) {
            Toast.makeText(this, "No Text Received", Toast.LENGTH_LONG).show();
        }
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
