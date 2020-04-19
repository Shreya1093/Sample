package com.example.fooddonation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UserHome extends Activity {
	
	Button logout,donor,password;
	String name=null,role=null;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_home);
		
		
		Intent ii=new Intent();
		name=getIntent().getExtras().getString("name");
		System.out.println("orphanage home name "+name);
		
		
		password = (Button) findViewById(R.id.password);
		logout = (Button) findViewById(R.id.logout);
		donor = (Button) findViewById(R.id.donor);
		

		password.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(UserHome.this,ChangePassword.class);
				ad.putExtra("name", name);
				ad.putExtra("role", role);
				startActivity(ad);
								}
        	 });
		
		
		logout.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(UserHome.this,MainActivity.class);
				startActivity(ad);
								}
        	 });
		
		donor.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(UserHome.this,UserSelectLoc.class);
				ad.putExtra("name", name);
				
				startActivity(ad);
								}
        	 });

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_home, menu);
		return true;
	}

}
