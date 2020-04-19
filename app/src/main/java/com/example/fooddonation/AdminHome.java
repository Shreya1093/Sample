package com.example.fooddonation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminHome extends Activity {
	
	Button orphanage,volunteer,logout,donor,food,dfood;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_home);
		
		orphanage = (Button) findViewById(R.id.orphanage);
		volunteer = (Button) findViewById(R.id.volunteer);
		logout = (Button) findViewById(R.id.logout);
		donor = (Button) findViewById(R.id.donor);
		food = (Button) findViewById(R.id.food);
		dfood = (Button) findViewById(R.id.dfood);
		
		dfood.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(AdminHome.this,AdminDonatedFood.class);
				startActivity(ad);
								}
        	 });
		
		
		
		food.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(AdminHome.this,ViewFood.class);
				startActivity(ad);
								}
        	 });
		
		orphanage.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(AdminHome.this,ViewOrphanage.class);
				startActivity(ad);
								}
        	 });
		
		volunteer.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(AdminHome.this,ViewVolunteer.class);
				startActivity(ad);
								}
        	 });
		
		logout.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(AdminHome.this,MainActivity.class);
				startActivity(ad);
								}
        	 });
		
		donor.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(AdminHome.this,ViewDonor.class);
				startActivity(ad);
								}
        	 });
		
			}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_home, menu);
		return true;
	}

}
