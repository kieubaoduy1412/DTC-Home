package com.example.dtchome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

public class SMSHandler extends BroadcastReceiver {
    private static final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static Context context;
    
    public SMSHandler(Context GlobalContext) {
    	context = GlobalContext;
	}

	// Retrieve SMS
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if(action.equals(ACTION_SMS_RECEIVED)){

            int contactId = -1;

            String str;
            str = null;
            showNotification(contactId, str);

            // ---send a broadcast intent to update the SMS received in the
            // activity---
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction("SMS_RECEIVED_ACTION");
            broadcastIntent.putExtra("sms", str);
            context.sendBroadcast(broadcastIntent);
        }

    }

    public static SmsMessage[] getMessagesFromIntent(Intent intent) {
        Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
        byte[][] pduObjs = new byte[messages.length][];

        for (int i = 0; i < messages.length; i++) {
            pduObjs[i] = (byte[]) messages[i];
        }
        byte[][] pdus = new byte[pduObjs.length][];
        int pduCount = pdus.length;
        SmsMessage[] msgs = new SmsMessage[pduCount];
        for (int i = 0; i < pduCount; i++) {
            pdus[i] = pduObjs[i];
            msgs[i] = SmsMessage.createFromPdu(pdus[i]);
        }
        return msgs;
    }

    protected void showNotification(int contactId, String message) {
        // Display notification...
    }
    
    /*
     * Gửi tin nhắn
     */
    public boolean sendSMSMessage(String Message) {
    	DataConfig config = new DataConfig( context );
    	
        try {
           SmsManager smsManager = SmsManager.getDefault();
           smsManager.sendTextMessage(config.getPhoneNumber(), null, Message, null, null);
        } 
        
        catch (Exception e) {
           e.printStackTrace();
           return false;
        }
        
        return true;
     }
}