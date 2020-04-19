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
import android.widget.Toast;

public class AcceptFood extends Activity {
	
	String name=null,address=null,sname=null,uname=null;
	Button back;
	
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accept_food);
		
		back=(Button)findViewById(R.id.back);
		
        name=getIntent().getExtras().getString("name");
        address=getIntent().getExtras().getString("address");
        sname=getIntent().getExtras().getString("sname");
        uname=getIntent().getExtras().getString("uname");
        System.out.println("accept food name is "+name);
        System.out.println("accept food address is "+address);
        System.out.println("accept food sname is "+sname);
        
    	back.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				
					
				try{

			          db=openOrCreateDatabase("donate",SQLiteDatabase.CREATE_IF_NECESSARY,null);					    
			         
			        }catch(Exception exception){

			            exception.printStackTrace();

			        }try{
			        	
			        	   System.out.println("accept food name is "+name);
			               System.out.println("accept food address is "+address);
			               System.out.println("accept food sname is "+sname);
			        	ContentValues newValues = new ContentValues();
			       String names=getIntent().getExtras().getString("name");
			       String add=getIntent().getExtras().getString("address");
			       String snames=getIntent().getExtras().getString("uname");
			        	newValues.put("volunteer",names);
			        	System.out.println("accept food name in new is "+name);
			        	

			        	String[] args = new String[]{snames,add};
			        	db.update("donatefood", newValues, "username=? AND address=?", args); 
			        	Toast.makeText(AcceptFood.this, "values "+address+" "+names, Toast.LENGTH_LONG).show();
			        	System.out.println("accept food name bi is "+names);
			               System.out.println("accept food address bi is "+add);
			               System.out.println("accept food sname bi is "+snames);
						
		            		Intent i = new Intent(AcceptFood.this,VolunteerHome.class);
		            		i.putExtra("name",names);
		            		i.putExtra("address",add);
		            		i.putExtra("sname",snames);
			            		startActivity(i);
				            	}catch(Exception exception){

			            exception.printStackTrace();

			        }
				
			}
			
	  });
         
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accept_food, menu);
		return true;
	}

}
