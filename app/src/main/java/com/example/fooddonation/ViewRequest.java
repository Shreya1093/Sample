package com.example.fooddonation;

import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewRequest extends Activity {
	
	TextView qrequest,time,address,username,orphanage;
	Button donate;
	private SQLiteDatabase newDB;
	SQLiteDatabase db;
	String add=null,name=null,status="yes",statusn="no";
	String a,b,cc,d,e,f;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_request);
		
		 name=getIntent().getExtras().getString("orphanage");
		    add=getIntent().getExtras().getString("address");
		    System.out.println("view request orp name "+name);
		    System.out.println("view request orp address "+add);
		    System.out.println("view request status "+statusn);

		    username = (TextView)findViewById(R.id.username);
		    orphanage = (TextView)findViewById(R.id.orphanage);
		    qrequest = (TextView)findViewById(R.id.qrequest);
		time = (TextView)findViewById(R.id.time);
		address = (TextView)findViewById(R.id.address);
		donate = (Button)findViewById(R.id.donate);
		System.out.println("view request orp username "+username.getText().toString());
	    System.out.println("view request orp orphanage "+orphanage.getText().toString());
	    System.out.println("view request qrequest "+qrequest.getText().toString());
	    System.out.println("view request orp time "+time.getText().toString());
	    System.out.println("view request orp address "+address.getText().toString());

		
       try {
			DBconnector dbHelper = new DBconnector(this.getApplicationContext());
			newDB = dbHelper.getWritableDatabase();
			Cursor c = newDB.rawQuery("SELECT * from request where address='"+add+"' and orphanage='"+name+"' and status='"+statusn+"'", null);
			
			
	    	if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {
	    				
		    		   
		    		    	  a =c.getString(c.getColumnIndex("username"));
		    		    	  b =c.getString(c.getColumnIndex("orphanage"));
		    		    	  cc =c.getString(c.getColumnIndex("qrequest"));
		    		    	  d =c.getString(c.getColumnIndex("time"));
		    		    	 e =c.getString(c.getColumnIndex("address"));
		    		    	 
		    		    	 
	    				
	    				
	    			}while (c.moveToNext());
	    		} 
	    	}			
		} catch (SQLiteException se ) {
      	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
      } 
	
       username.setText(a);
       orphanage.setText(b);
       qrequest.setText(cc);
       time.setText(d);
       address.setText(e);
       
		
		
		donate.setOnClickListener(
				new OnClickListener()
				{
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
			       String names=getIntent().getExtras().getString("orphanage");
			       String add=getIntent().getExtras().getString("address");
			        	newValues1.put("status",status);
			        	System.out.println("orphanage accept food name in new is "+names);
			        	

			        	String[] args = new String[]{names,add};
			        	db.update("request", newValues1, "orphanage=? AND address=?", args); 
			        	System.out.println("orphanage accept food name bi is "+names);
			               System.out.println(" orphanage accept food address bi is "+add);
						
		            		
				            	}catch(Exception exception){

			            exception.printStackTrace();

			        }

				  
				 
				 
         
         	
	            	
		            	

Intent it=new Intent(ViewRequest.this,CheckHome.class);

startActivity(it);
					}});
					
				}

		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_request, menu);
		return true;
	}

}
