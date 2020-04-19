package com.example.fooddonation;

import java.util.ArrayList;
import java.util.List;



import com.example.fooddonation.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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


public class MainActivity extends Activity {
	
	EditText username,password;
	Button login,register;
	String role = null;
	 SQLiteDatabase db;
	 String aa = "admin";
	 String bb = "checkpoint";
	 String u,p;
	 
	 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.register);
		
		

		
		final List<String> list=new ArrayList<String>();
		list.add("Admin");
		list.add("CheckPoint");
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
			
						
			register.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(MainActivity.this,RegisterActivity.class);
					startActivity(ad);
									}
	        	 });
			
			login.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					if(username.getText().toString().equals("")||password.getText().toString().equals("")){
						
						Toast.makeText(MainActivity.this, "PLz enter the fields..!", Toast.LENGTH_LONG).show();
					}
					else{
						
						 try{

						          db=openOrCreateDatabase("donate",SQLiteDatabase.CREATE_IF_NECESSARY,null);					    
						         
						        }catch(Exception exception){

						            exception.printStackTrace();

						        }try{
						        	u=username.getText().toString();
						        	p=password.getText().toString();
						        	if(username.getText().toString().equals(aa)&& password.getText().toString().equals(aa)&&role.equals("Admin")){
						        		Toast.makeText(MainActivity.this, "Welcome "  + u , Toast.LENGTH_LONG).show();
										Intent ad = new Intent(MainActivity.this,AdminHome.class);
										ad.putExtra("s", u);
										startActivity(ad);
						        	}
						        	else if(username.getText().toString().equals(bb)&& password.getText().toString().equals(bb)&&role.equals("CheckPoint")){
						        		Toast.makeText(MainActivity.this, "Welcome "  + u , Toast.LENGTH_LONG).show();
										Intent ad = new Intent(MainActivity.this,CheckHome.class);
										ad.putExtra("s", u);
										startActivity(ad);
						        	}
						        	else{
						        	
						        	
						        	 Cursor cc = db.rawQuery("select * from reg where username = '"+u+"' and password = '"+p+"' and role='"+role+"' ", null);
								     cc.moveToFirst();						            String temp="";					       
							            if (cc != null) {
							            	if(cc.getCount() > 0)
							            	{
							            	//return true;
							            scan g=new scan();
							            g.execute();
							         
							            
							            System.out.println("name in main activity is "+u);
							             if(role=="Donor"){
													Toast.makeText(MainActivity.this, "Welcome "  + u , Toast.LENGTH_LONG).show();
													Intent ad = new Intent(MainActivity.this,DonorHome.class);
													ad.putExtra("name" , username.getText().toString());
													ad.putExtra("role" , role);
													System.out.println(u);
													System.out.println(username.getText().toString());
													startActivity(ad);
												}
												else if(role=="Orphanage"){
													Toast.makeText(MainActivity.this, "Welcome "  + u , Toast.LENGTH_LONG).show();
													Intent ad = new Intent(MainActivity.this,OrphanageHome.class);
													ad.putExtra("name" , username.getText().toString());
													ad.putExtra("role" , role);
													System.out.println(u);
													System.out.println(username.getText().toString());
													startActivity(ad);
												}
												else if(role=="User"){
													Toast.makeText(MainActivity.this, "Welcome "  + u , Toast.LENGTH_LONG).show();
													Intent ad = new Intent(MainActivity.this,UserHome.class);
													ad.putExtra("name" , username.getText().toString());
													ad.putExtra("role" , role);
													System.out.println(u);
													System.out.println(username.getText().toString());
													startActivity(ad);
												}
												else if(role=="Volunteer"){
													Toast.makeText(MainActivity.this, "Welcome "  + u , Toast.LENGTH_LONG).show();
													Intent ad = new Intent(MainActivity.this,VolunteerHome.class);
													ad.putExtra("name" , username.getText().toString());
													ad.putExtra("role" , role);
													System.out.println(u);
													System.out.println(username.getText().toString());
													startActivity(ad);
												}
						            		    
							            		
							            	}else{
							            		 Toast.makeText(MainActivity.this, "Login Fails..!", Toast.LENGTH_LONG).show();
							            	}
							            	}
							            //	return false;
							           
						        }}catch(Exception exception){

						            exception.printStackTrace();

						        }
							
							 
							}
						 	
		

					
				}
			});}
	public class scan extends AsyncTask<String, String, String>{

		private ProgressDialog pd;

		protected void onPreExecute() {
			super.onPreExecute();
		 pd = new ProgressDialog(MainActivity.this);
		 pd.setTitle("Please Wait");
		 pd.setMessage("Logging....");
		 pd.setMax(200);
		 pd.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}



	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
