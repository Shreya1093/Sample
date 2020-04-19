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

public class ViewDonorDetailsV extends ListActivity {
	String name=null,address=null,sname=null,uname=null;
	 ArrayList<String> results = new ArrayList<String>();
	 ArrayList<String> as=new ArrayList<String>();
	private SQLiteDatabase newDB;
	int i=0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent ii=getIntent();
        name=getIntent().getExtras().getString("name");
        address=getIntent().getExtras().getString("address");
        sname=getIntent().getExtras().getString("sname");
        uname=getIntent().getExtras().getString("uname");

        openAndQueryDatabase();
        
        displayResultList();
        
        
    }
	private void displayResultList() {
		TextView tView = new TextView(this);
        tView.setText("Donor Details");
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
			Cursor c = newDB.rawQuery("SELECT * from donatefood where username ='"+uname+"' and address='"+address+"'", null);
			
			
	    	if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {

	    				String username =c.getString(c.getColumnIndex("username"));
	    		    	String quantity =c.getString(c.getColumnIndex("quantity"));
	    		    	String type =c.getString(c.getColumnIndex("type"));
	    		    	String time =c.getString(c.getColumnIndex("time"));
	    		    	String done="User Name :"+username+"\n Quantity :"+quantity+"\nFood type :"+type+"\nCooked time :"+time;		
	    				results.add(done);
	    				
	    			}while (c.moveToNext());
	    		} 
	    	}			
		} catch (SQLiteException se ) {
        	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } 
	}
	}
