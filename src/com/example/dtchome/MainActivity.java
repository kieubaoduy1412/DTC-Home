package com.example.dtchome;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity{
	private Context context;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    getActionBar().hide();
		setContentView(R.layout.activity_main);
//		final ViewGroup rootView;
//		final ImageView imageview = (ImageView) findViewById(R.id.lamp);
//		final DataConfig config = new DataConfig( context );
//	    final SMSHandler SMSHandler = new SMSHandler( context );
//	    final Integer[] LampsStatus = config.GetLampStatus();
//	    final int LampID = 0;
//		imageview.setOnClickListener(new View.OnClickListener() {
//		    @Override
//		    public void onClick(View v) {
//		    	Integer LampStatus = config.GetLampStatus(LampID);
//				String SmsContent = "LAMP:" + ( LampStatus == 1 ? "0" : "1" );
//				
//				if( SMSHandler.sendSMSMessage(SmsContent) == true ){
//					Toast.makeText(context, getString(R.string.lamp) + (LampStatus == 0 ? getString(R.string.is_turned_on) : getString(R.string.is_turned_off) ), Toast.LENGTH_SHORT).show();
//					imageview.setImageResource(LampStatus == 0 ? R.drawable.lamp_icon_active : R.drawable.lamp_icon);
//					config.SetLampStatus(LampID, LampStatus == 0 ? 1 : 0);
//				}else{
//					Toast.makeText(context, getString(R.string.SmsSendError), Toast.LENGTH_SHORT).show();
//				}
//		    }
//		});
	}
}
