package edu.slcc.jasonshepherd.hangdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import static android.telephony.SmsMessage.createFromPdu;

public class IncomingSms extends BroadcastReceiver {

    final SmsManager sms = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving

        final Bundle bundle = intent.getExtras();

        try {
            if (bundle != null) {
                Log.d("MYLOG", "Bundle: " + bundle);
                // get pdu from bundle
                final Object[] pdus = (Object[]) bundle.get("pdus");
                // get format of bundle
                String format = bundle.getString("format");

                for (int i = 0; i < pdus.length; i++) {

                    SmsMessage currentMessage = createFromPdu((byte[]) pdus[i], format);
                    //SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[0]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    //String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    Log.d("MYLOG", "phone: " + phoneNumber + "; message: " + message);

                    //Show alert
                    Toast.makeText(context, "Text received from " + phoneNumber, Toast.LENGTH_LONG).show();

                    //Set up to store preferences * add context because getSharedPreferences() needs
                    SharedPreferences preferences = context.getSharedPreferences("TEXT_MSGS", Context.MODE_PRIVATE);
                    //start the preference editor
                    SharedPreferences.Editor editor= preferences.edit();
                    //get previous scores USING THE KEY!
                    Log.d("MYLOG", "TextedWord: " + message);
                    editor.putString("TextedWord", message);
                    //saves buffer
                    editor.commit();
                }

            }
        } catch (Exception e) {
            Log.e("MYLOG", "Exception smsReceiver" + e);
        }
    }
}
