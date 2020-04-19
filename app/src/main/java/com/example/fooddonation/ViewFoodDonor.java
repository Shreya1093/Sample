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

public class ViewFoodDonor extends ListActivity {
	String address=null;
	 ArrayList<String> results = new ArrayList<String>();
	 ArrayList<String> as=new ArrayList<String>();
	private SQLiteDatabase newDB;
	int i=0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent ii=getIntent();
        address=getIntent().getExtras().getString("address");
        System.out.println("view food donor address "+address);

        openAndQueryDatabase();
        
        displayResultList();
        
        
    }
	private void displayResultList() {
		TextView tView = new TextView(this);
        tView.setText("Food Donors List");
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
			Cursor c = newDB.rawQuery("SELECT * from donatefood where address ='"+address+"' and quantity!=0 ", null);
			
			
	    	if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {

	    				String username =c.getString(c.getColumnIndex("username"));
	    		    	String quantity =c.getString(c.getColumnIndex("quantity"));
	    		    	String type =c.getString(c.getColumnIndex("type"));
	    		    	String time =c.getString(c.getColumnIndex("time"));
	    		    	String volunteer =c.getString(c.getColumnIndex("volunteer"));
	    		    	String done="Donor Name :"+username+"\nQuantity :"+quantity+"\nType of food :"+type+"\nCooked time :"+time+"\nVolunteer Name :"+volunteer;		
	    				results.add(done);
	    				
	    			}while (c.moveToNext());
	    		} 
	    	}			
		} catch (SQLiteException se ) {
        	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } 
	}
	}
