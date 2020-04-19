package com.example.fooddonation;

import java.util.ArrayList;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ViewVolunteer extends ListActivity {
	 ArrayList<String> results = new ArrayList<String>();
	 ArrayList<String> as=new ArrayList<String>();
	private SQLiteDatabase newDB;
	int i=0;
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       
       openAndQueryDatabase();
       
       displayResultList();
       
       
   }
	private void displayResultList() {
		TextView tView = new TextView(this);
       tView.setText("Volunteers List");
       getListView().addHeaderView(tView);
       
       setListAdapter(new ArrayAdapter<String>(this,
               android.R.layout.simple_list_item_1, results));
       
    final   ListView lv=getListView();
       lv.setTextFilterEnabled(true);


       lv.setOnItemClickListener(new OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

        	   final int pos1=position;
         	  new AlertDialog.Builder(ViewVolunteer.this)
               .setTitle("Food Donation")
               .setMessage("Select any option")
               .setPositiveButton("View", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                	   Intent anotherActivityIntent = new Intent(getApplicationContext(), ViewVolunteerDetails.class);
                       String name=(String)(lv.getItemAtPosition(pos1));
                       anotherActivityIntent.putExtra("s", name);
                         i++;
                         startActivity(anotherActivityIntent);  
                   }
               })
               .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       DBconnector dbc=new DBconnector(ViewVolunteer.this);
                       dbc.deleteUser((String)lv.getItemAtPosition(pos1));
                       Intent anotherActivityIntent = new Intent(getApplicationContext(), AdminHome.class);
                     
                         startActivity(anotherActivityIntent); 
                   }
               }).show();
        	   
        	   
                 
           
           }
         });

      
		
	}
	private void openAndQueryDatabase() {
		try {
			DBconnector dbHelper = new DBconnector(this.getApplicationContext());
			newDB = dbHelper.getWritableDatabase();
			Cursor c = newDB.rawQuery("SELECT * from reg where role='Volunteer'", null);
			
			
	    	if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {
	    				String name =c.getString(c.getColumnIndex("username"));
	    				
	    				results.add(name);
	    				
	    			}while (c.moveToNext());
	    		} 
	    	}			
		} catch (SQLiteException se ) {
       	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
       } 
	}
	}
