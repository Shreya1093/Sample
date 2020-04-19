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

public class ViewDonorLoc extends ListActivity {
	 ArrayList<String> results = new ArrayList<String>();
	 ArrayList<String> as=new ArrayList<String>();
	private SQLiteDatabase newDB;
	int i=0;
	String name=null,address=null,sname=null,uname=null;
	
/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    Intent ii = new Intent();
	name=getIntent().getStringExtra("name");
	address=getIntent().getStringExtra("address");
	sname=getIntent().getStringExtra("sname");
	System.out.println("view donor loc name "+name);
	System.out.println("view donor loc address "+address);
    
    
    
    openAndQueryDatabase();
    
    displayResultList();
    
    
}
	private void displayResultList() {
		TextView tView = new TextView(this);
    tView.setText("Donors List");
    getListView().addHeaderView(tView);
    
    setListAdapter(new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, results));
    
 final   ListView lv=getListView();
    lv.setTextFilterEnabled(true);


    lv.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

        	
        	
        	AlertDialog.Builder dialog = new AlertDialog.Builder(ViewDonorLoc.this);
        	dialog.setCancelable(false);
        	dialog.setTitle(getResources().getString(R.string.app_name));
        	dialog.setMessage("Do you want to view/accept");
        	dialog.setPositiveButton("View", new DialogInterface.OnClickListener() {

        		@Override
        		public void onClick(DialogInterface dialog, int which) 
        		{
        			Intent anotherActivityIntent = new Intent(getApplicationContext(), ViewDonorDetailsV.class);
        			anotherActivityIntent.putExtra("name",name);
        			anotherActivityIntent.putExtra("address",address);
        			anotherActivityIntent.putExtra("sname",sname);
        			anotherActivityIntent.putExtra("uname",uname);
                         startActivity(anotherActivityIntent); 

        		}
        	});
        	dialog.setNegativeButton("Accept", new DialogInterface.OnClickListener() {

        		@Override
        		public void onClick(DialogInterface dialog, int which) 
        		{
        			Intent anotherActivityIntent = new Intent(getApplicationContext(), AcceptFood.class);
        			anotherActivityIntent.putExtra("name",name);
        			anotherActivityIntent.putExtra("address",address);
        			anotherActivityIntent.putExtra("sname",sname);
        			anotherActivityIntent.putExtra("uname",uname);
                         startActivity(anotherActivityIntent); 

        		}
        	});
        	dialog.show();	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
             /* Intent anotherActivityIntent = new Intent(getApplicationContext(), ViewDonorLocD.class);
           String sname=(String)(lv.getItemAtPosition(position));
           System.out.println("view donor loc snames is "+sname);
           anotherActivityIntent.putExtra("name", name);
           anotherActivityIntent.putExtra("address", address);
           anotherActivityIntent.putExtra("sname", sname);
           System.out.println("view donor loc sname is "+sname);
             i++;
             startActivity(anotherActivityIntent); */
        
        }
      });

   
		
	}
	private void openAndQueryDatabase() {
		try {
			
			System.out.println("view donor loc name "+name);
			System.out.println("view donor loc address "+address);
			DBconnector dbHelper = new DBconnector(this.getApplicationContext());
			newDB = dbHelper.getWritableDatabase();
			Cursor c = newDB.rawQuery("SELECT * from donatefood where address='"+address+"'", null);
			
			
	    	if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {
	    				uname =c.getString(c.getColumnIndex("username"));
	    				
	    				results.add(uname);
	    				
	    			}while (c.moveToNext());
	    		} 
	    	}}	
	    	
	    	
		catch (SQLiteException se ) {
    	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
    } 
	}}
	

