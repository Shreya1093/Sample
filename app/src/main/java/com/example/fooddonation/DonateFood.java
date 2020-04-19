package com.example.fooddonation;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DonateFood extends Activity {
	
	EditText quantity,type,time,address;
	String name=null;
	Button donate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_donate_food);
		
		quantity = (EditText) findViewById(R.id.quantity);
		type = (EditText) findViewById(R.id.type);
		time = (EditText) findViewById(R.id.time);
		address = (EditText) findViewById(R.id.address);
		donate = (Button) findViewById(R.id.donate);
		
		Intent i = new Intent();
		name=getIntent().getStringExtra("name");
		System.out.println("name in donate food is "+name);
		
		donate.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				if(quantity.getText().toString().equals("") && type.getText().toString().equals("") && time.getText().toString().equals("")
						 && address.getText().toString().equals("")){
					 Toast.makeText(DonateFood.this, "Please fill the blank",Toast.LENGTH_LONG).show();	
				}
				

				else if (!isValidTime(time.getText().toString())) {
					time.setError("Invalid Time");
					Toast.makeText(DonateFood.this, "Time must be in 24 hour format ",Toast.LENGTH_LONG).show();
				}

				else {
					
					DBconnector dbc = new DBconnector(DonateFood.this);
					  
					  HashMap<String , String> map = new  HashMap<String, String>();
					  map.put("username",name);
					  map.put("quantity",quantity.getText().toString());
					  map.put("type",type.getText().toString());
					  map.put("time",time.getText().toString());
					  map.put("address",address.getText().toString());
					  map.put("volunteer","1no");
					  map.put("orphanage","1no");
					  dbc.insert_donatefood(map);
					  Toast.makeText(DonateFood.this, "Successfully uploaded....!", 200).show();				  
					  quantity.setText("");
					  type.setText("");
					  time.setText("");
					  address.setText("");
					  
					  
					  
					  Intent i = new Intent(DonateFood.this, DonorHome.class);
					  i.putExtra("name", name);
					  startActivity(i);
				}
			}
			
	  });

		
	}
	
	
	private boolean isValidEmail(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	


	private boolean isValidTime(String time) {
		String TIME_PATTERN="^[0-2]+[0-9]+[:]+[0-5][0-9]$";
		
			Pattern pattern = Pattern.compile(TIME_PATTERN);
			Matcher matcher = pattern.matcher(time);
			return matcher.matches();
			
	}
		


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.donate_food, menu);
		return true;
	}

}
