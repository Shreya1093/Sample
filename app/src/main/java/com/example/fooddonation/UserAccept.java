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

public class UserAccept extends Activity {
	
	EditText qrequest;
	TextView quantity,type,time,address,username;
	Button donate;
	private SQLiteDatabase newDB;
	SQLiteDatabase db;
	String add=null,name=null,sname=null,status="no";
	String a,b,cc,d,e,f;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_accept);
		
		 name=getIntent().getExtras().getString("name");
		 sname=getIntent().getExtras().getString("sname");
		    add=getIntent().getExtras().getString("address");

		    username = (TextView)findViewById(R.id.username);
		quantity = (TextView)findViewById(R.id.quantity);
		type = (TextView)findViewById(R.id.type);
		time = (TextView)findViewById(R.id.time);
		address = (TextView)findViewById(R.id.address);
		qrequest = (EditText)findViewById(R.id.request);
		donate = (Button)findViewById(R.id.donate);
		
       try {
			DBconnector dbHelper = new DBconnector(this.getApplicationContext());
			newDB = dbHelper.getWritableDatabase();
			Cursor c = newDB.rawQuery("SELECT * from donatefood where address='"+add+"'", null);
			
			
	    	if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {
	    				
		    		   
		    		    	  a =c.getString(c.getColumnIndex("username"));
		    		    	  b =c.getString(c.getColumnIndex("quantity"));
		    		    	  cc =c.getString(c.getColumnIndex("type"));
		    		    	  d =c.getString(c.getColumnIndex("time"));
		    		    	 e =c.getString(c.getColumnIndex("address"));
		    		    	 
		    		    	 
	    				
	    				
	    			}while (c.moveToNext());
	    		} 
	    	}			
		} catch (SQLiteException se ) {
      	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
      } 
	
       username.setText(a);
       quantity.setText(b);
       type.setText(cc);
       time.setText(d);
       address.setText(e);
       
		
		
		donate.setOnClickListener(
				new OnClickListener()
				{
					
			

					
					@Override
					public void onClick(View v){
						//TODO Auto-generated method stub
						
						int rq=Integer.parseInt(qrequest.getText().toString());

						if(rq>4){
							
							Toast.makeText(UserAccept.this, "Your limit is only 4 plates", 200).show();
							
						}
					else{	

		try{

	          db=openOrCreateDatabase("donate",SQLiteDatabase.CREATE_IF_NECESSARY,null);					    
	         
	        }catch(Exception exception){

	            exception.printStackTrace();

	        }try{
	        	ContentValues newValues = new ContentValues();
	        	
	        	String bg = username.getText().toString();
	        	newValues.put("username", bg);
	        	String fn = quantity.getText().toString();
	        	newValues.put("quantity", fn);
	        	String mn = type.getText().toString();
	        	newValues.put("type", mn);
	        	String a = time.getText().toString();
	        	newValues.put("time", a);
	        	String b = address.getText().toString();
	        	newValues.put("address", b);
	        	String c = qrequest.getText().toString();
	        	newValues.put("qrequest", c);
	        	
	        	


	        	DBconnector dbc = new DBconnector(UserAccept.this);

	        	HashMap<String , String> map = new  HashMap<String, String>();
				  map.put("username",username.getText().toString());
				  map.put("quantity",quantity.getText().toString());
				  map.put("type",type.getText().toString());
				  map.put("time",time.getText().toString());
				  map.put("address",address.getText().toString());
				  map.put("orphanage",name);
				  map.put("qrequest",qrequest.getText().toString());
				  map.put("status",status);
				  dbc.insert_request(map);
				  Toast.makeText(UserAccept.this, "Requested successfully ....!", 200).show();		
				  
				  
				  int rquantity=Integer.parseInt(quantity.getText().toString());
				  int qrrequest=Integer.parseInt(qrequest.getText().toString());
				  
				  int remaining=rquantity-qrrequest;
				  
				  if(qrrequest>4){
					  
					  Toast.makeText(UserAccept.this, "You can request upto maximum of 4 plates only..", 200).show();
					  
				  }else{
				  
				  try{
					 

			          db=openOrCreateDatabase("donate",SQLiteDatabase.CREATE_IF_NECESSARY,null);					    
			         
			        }catch(Exception exception){

			            exception.printStackTrace();

			        }try{
			        	
			        
			        	   /*System.out.println("orphanage accept  name is "+name);
			               System.out.println("orphanage accept  address is "+address);
			               System.out.println("orphanage accept  sname is "+sname);*/
			        	ContentValues newValues1 = new ContentValues();
			       String names=getIntent().getExtras().getString("name");
			       String add=getIntent().getExtras().getString("address");
			       String snames=getIntent().getExtras().getString("sname");
			        	newValues1.put("orphanage",names);
			        	newValues1.put("quantity",remaining);
			        	System.out.println("orphanage accept food name in new is "+names +"quantity is "+remaining);
			        	

			        	String[] args = new String[]{snames,add};
			        	db.update("donatefood", newValues1, "username=? AND address=?", args);
			        	System.out.println("orphanage accept food name bi is "+names);
			               System.out.println(" orphanage accept food address bi is "+add);
			               System.out.println("orphanage accept food sname bi is "+snames);
						
		            		
				            	}catch(Exception exception){

			            exception.printStackTrace();

			        }

				  
				 
				 
         
         	
	            	
		            	}}catch(Exception exception){

	            exception.printStackTrace();

	        }

Intent it=new Intent(UserAccept.this,UserHome.class);
it.putExtra("name", name);
startActivity(it);
					}
					
					}	});

		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_accept, menu);
		return true;
	}

}
