package com.example.fooddonation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class RegisterActivity extends Activity {
	
	EditText username,password,email,phone,address;
	Button button;
	String role = null;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		email = (EditText) findViewById(R.id.email);
		phone = (EditText) findViewById(R.id.phone);
		address = (EditText) findViewById(R.id.address);
		button = (Button) findViewById(R.id.register);
		
		final List<String> list=new ArrayList<String>();
		
		 list.add("Donor");
		 list.add("Orphanage");
			list.add("Volunteer");
			list.add("User");
		
			
			final Spinner sp=(Spinner) findViewById(R.id.select);
			ArrayAdapter<String> adp= new ArrayAdapter<String>(this,
			                                android.R.layout.simple_list_item_1,list);
			adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			sp.setAdapter(adp);
			
			sp.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					role = sp.getItemAtPosition(arg2).toString();
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			button.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					
					
					
					
					
					
					if(username.getText().toString().equals("") && password.getText().toString().equals("") && email.getText().toString().equals("")
							 && phone.getText().toString().equals("") && address.getText().toString().equals("")){
						 Toast.makeText(RegisterActivity.this, "Please fill the blank",Toast.LENGTH_LONG).show();	
					}
					
					

					else if (!isValidEmail(email.getText().toString())) {
						email.setError("Invalid Email");
						Toast.makeText(RegisterActivity.this, "email must be in the format of abc@mail.com ",Toast.LENGTH_LONG).show();
					}

					
					else if (!isValidPassword(password.getText().toString())) {
						password.setError("Invalid Password");
						Toast.makeText(RegisterActivity.this, "password must contain atleast 7 characters ",Toast.LENGTH_LONG).show();
					}
					
					else if (!isValidPhone(phone.getText().toString())) {
						phone.setError("Invalid Phone");
						Toast.makeText(RegisterActivity.this, "phone number must contain 10 digits",Toast.LENGTH_LONG).show();
					}
					
					else {
						
						DBconnector dbc = new DBconnector(RegisterActivity.this);
						  
						  HashMap<String , String> map = new  HashMap<String, String>();
						  map.put("username",username.getText().toString());
						  map.put("password",password.getText().toString());
						  map.put("email",email.getText().toString());
						  map.put("phone",phone.getText().toString());
						  map.put("address",address.getText().toString());
						  map.put("role",role);
						  dbc.insert_reg(map);
						  Toast.makeText(RegisterActivity.this, "Registered successfully ....!", 200).show();				  
						  username.setText("");
						  password.setText("");
						  email.setText("");
						  phone.setText("");
						  address.setText("");
						  Intent i = new Intent(RegisterActivity.this, MainActivity.class);
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
	
	private boolean isValidPassword(String pass) {
		if (pass != null && pass.length() > 6) {
			return true;
		}
		return false;
	}

	private boolean isValidPhone(String phone) {
		if (phone != null && phone.length() ==10 ) {
			return true;
		}
		return false;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

}
