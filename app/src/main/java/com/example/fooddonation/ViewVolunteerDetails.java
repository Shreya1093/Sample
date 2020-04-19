package com.example.fooddonation;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ViewVolunteerDetails extends ListActivity {
	String sname=null;
	 ArrayList<String> results = new ArrayList<String>();
	 ArrayList<String> as=new ArrayList<String>();
	private SQLiteDatabase newDB;
	int i=0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i=getIntent();
        sname=i.getExtras().getString("s");

        openAndQueryDatabase();
        
        displayResultList();
        
        
    }
	private void displayResultList() {
		TextView tView = new TextView(this);
        tView.setText("Volunteer Details");
        getListView().addHeaderView(tView);
        
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results));
        
     final   ListView lv=getListView();
        lv.setTextFilterEnabled(true);


        
       
		
	}
	private void openAndQueryDatabase() {
		try {
			DBconnector dbHelper = new DBconnector(this.getApplicationContext());
			newDB = dbHelper.getWritableDatabase();
			Cursor c = newDB.rawQuery("SELECT * from reg where username ='"+sname+"' and role='Volunteer'", null);
			
			
	    	if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {

	    				String username =c.getString(c.getColumnIndex("username"));
	    		    	String email =c.getString(c.getColumnIndex("email"));
	    		    	String phone =c.getString(c.getColumnIndex("phone"));
	    		    	String address =c.getString(c.getColumnIndex("address"));
	    		    	String done="Volunteer Name :"+username+"\nEmail :"+email+"\nPhone Number :"+phone+"\nAddress :"+address;		
	    				results.add(done);
	    				
	    			}while (c.moveToNext());
	    		} 
	    	}			
		} catch (SQLiteException se ) {
        	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } 
	}
	}
