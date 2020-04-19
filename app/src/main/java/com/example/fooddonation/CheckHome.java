package com.example.fooddonation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CheckHome extends Activity {
	
	Button request,logout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_home);
		
		request=(Button) findViewById(R.id.request);
		logout=(Button) findViewById(R.id.logout);
		
		request.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(CheckHome.this,ViewLocation.class);
				startActivity(ad);
								}
        	 });
		
		logout.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(CheckHome.this,MainActivity.class);
				startActivity(ad);
								}
        	 });

		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check_home, menu);
		return true;
	}

}
