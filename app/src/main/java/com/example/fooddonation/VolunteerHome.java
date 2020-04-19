package com.example.fooddonation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class VolunteerHome extends Activity {
	
	Button logout,donor,password;
	String name=null,role=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volunteer_home);
		
		password = (Button) findViewById(R.id.password);
		logout = (Button) findViewById(R.id.logout);
		donor = (Button) findViewById(R.id.donor);
		
		Intent i = new Intent();
		name=getIntent().getStringExtra("name");
		

		password.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(VolunteerHome.this,ChangePassword.class);
				ad.putExtra("name", name);
				ad.putExtra("role", role);
				startActivity(ad);
								}
        	 });

		
		logout.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(VolunteerHome.this,MainActivity.class);
				startActivity(ad);
								}
        	 });
		
		donor.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(VolunteerHome.this,VolunteerViewDonor.class);
				ad.putExtra("name", name);
				startActivity(ad);
								}
        	 });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.volunteer_home, menu);
		return true;
	}

}
