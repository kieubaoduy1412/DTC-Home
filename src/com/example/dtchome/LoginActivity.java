package com.example.dtchome;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;


public class LoginActivity extends Activity {

	private static Runnable nextActivityRunnable = null;
	private static Handler nextActivityHandler = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    getActionBar().hide();
		setContentView(R.layout.activity_login);

		nextActivityRunnable = new Runnable() {
            @Override
            public void run() {
            	nextActivity();
            }
        };
        nextActivityHandler = new Handler();
		nextActivityHandler.postDelayed(nextActivityRunnable, 3000);
		
		// Click vào màn hình chuyển đến giao diện đăng nhập
		LinearLayout app_layer = (LinearLayout) findViewById (R.id.container);
        app_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	nextActivityHandler.removeCallbacks(nextActivityRunnable);
            	nextActivity();
            }
        });
	}
	
	protected void nextActivity(){
		Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
		LoginActivity.this.startActivity(mainIntent);
		LoginActivity.this.finish();
	}
	
	/*
	 * Dừng chuyển giao diện đăng nhập nếu như nhấn back để kết thúc ngay tại trang home
	 */
	public void onBackPressed(){
		//handler lúc đầu bằng null sau đó sẽ chuyển sang 1 lớp trong hàm Oncreate
		if(nextActivityHandler != null) {
	        if (nextActivityRunnable != null){
	        	nextActivityHandler.removeCallbacks(nextActivityRunnable);
	        }
	    }
		super.onBackPressed();
	}
	
	/*
	 * Dừng hết việc chuẩn bị chuyển giao diện đăng nhập nếu như dừng app
	 */
	protected void onPause() {     
		if(nextActivityHandler != null) {
	        if (nextActivityRunnable != null){
	        	nextActivityHandler.removeCallbacks(nextActivityRunnable);
	        }
	    }
		super.onPause();          
	}
}
