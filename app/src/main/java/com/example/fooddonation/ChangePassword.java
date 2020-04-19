package com.example.fooddonation;

import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends Activity {
	
	String name=null,role=null;
	EditText cpassword,npassword;
	SQLiteDatabase db;
	Button submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		
		cpassword=(EditText) findViewById(R.id.cpassword);
		npassword=(EditText) findViewById(R.id.npassword);
		submit=(Button) findViewById(R.id.submit);
		
		name=getIntent().getStringExtra("name");
		role=getIntent().getStringExtra("role");
		
		String cp=cpassword.getText().toString();
		String np=npassword.getText().toString();
		System.out.println("old password:"+cp);
		System.out.println("new password:"+np);
		
		submit.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v){
						//TODO Auto-generated method stub
					

		try{

	          db=openOrCreateDatabase("donate",SQLiteDatabase.CREATE_IF_NECESSARY,null);					    
	         
	        }catch(Exception exception){

	            exception.printStackTrace();

	        }				  
				  try{
			        	
			        
			        	ContentValues newValues1 = new ContentValues();
			       String name=getIntent().getExtras().getString("name");
			       String role=getIntent().getExtras().getString("role");
			       System.out.println("name:"+name);
			       System.out.println("role:"+role);
			       String cp=cpassword.getText().toString();
			       String np=npassword.getText().toString();
			       System.out.println("old pass:"+cp);
			       System.out.println("new pass:"+np);

			        	newValues1.put("password",np);
			        	

			        	String[] args = new String[]{name,role,cp};
			        	db.update("reg", newValues1, "username=? AND role=? AND password=?", args); 
		            		
				            	}catch(Exception exception){

			            exception.printStackTrace();

			        }

				  
				 
				 
          
          	
	            	
		            	

Intent aaa=new Intent(ChangePassword.this,MainActivity.class);
startActivity(aaa);
					}});
					
				}

		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_password, menu);
		return true;
	}

}
