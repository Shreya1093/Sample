package com.example.fooddonation;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class UserSelectLoc extends Activity {
	
	
	Button submit;
	SQLiteDatabase db;
	String name=null,address=null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_select_loc);
		
		
		Intent ii=getIntent();
        name=getIntent().getExtras().getString("name");
        System.out.println("user select location name "+name);
		
		submit=(Button) findViewById(R.id.submit);
		try{

	          db=openOrCreateDatabase("donate",SQLiteDatabase.CREATE_IF_NECESSARY,null);					    
	         
	        }catch(Exception exception){

	            exception.printStackTrace();

	        }
		
		 Cursor cc = db.rawQuery("select * from donatefood ", null);
	     cc.moveToFirst();						            String temp="";					       
          if (cc != null) {
          	if(cc.getCount() > 0)
          	{
          	//return true;
       
       
          	
		
		final List<String> list=new ArrayList<String>();
		
		
		list.add(cc.getString(cc.getColumnIndex("address")));
		
          	
			
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
					address = sp.getItemAtPosition(arg2).toString();
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
				
			}
				
				
				
			);}}

          
          submit.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(UserSelectLoc.this,UserViewDonor.class);
					ad.putExtra("address", address);
					ad.putExtra("name", name);
					System.out.println("address is "+address);
					System.out.println("name is "+name);
				
					startActivity(ad);
									}
	        	 });

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_select_loc, menu);
		return true;
	}

}
