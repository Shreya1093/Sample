package com.example.fooddonation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DonorHome extends Activity {
	
	
	Button donate,logout,volunteer,password;
	String name=null,role=null;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_donor_home);
		
		
		password = (Button) findViewById(R.id.password);
		donate = (Button) findViewById(R.id.donate);
		logout = (Button) findViewById(R.id.logout);
		volunteer = (Button) findViewById(R.id.volunteer);
		
	
		name=getIntent().getStringExtra("name");
		role=getIntent().getStringExtra("role");

		System.out.println("donor home name is "+name);
		
		donate.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(DonorHome.this,DonateFood.class);
				
				
		    
				ad.putExtra("name", name);
				startActivity(ad);
								}
        	 });
		
		logout.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(DonorHome.this,MainActivity.class);
				startActivity(ad);
								}
        	 });
		password.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(DonorHome.this,ChangePassword.class);
				ad.putExtra("name", name);
				ad.putExtra("role", role);
				startActivity(ad);
								}
        	 });
		volunteer.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(DonorHome.this,DonatedFood.class);
				ad.putExtra("name", name);
				startActivity(ad);
								}
        	 });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.donor_home, menu);
		return true;
	}

}
