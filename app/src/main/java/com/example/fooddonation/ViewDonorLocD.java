package com.example.fooddonation;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ViewDonorLocD extends Activity {
	
	String name=null,address=null,sname=null;
	Button b;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_donor_loc_d);
		Intent i=getIntent();
        name=i.getExtras().getString("name");
        System.out.println("view donor loc d name:"+name);
        address=i.getExtras().getString("address");
        System.out.println("view donor loc d address:"+address);
        sname=i.getExtras().getString("sname");
        System.out.println("view donor loc d sname:"+sname);
	
	b = (Button) findViewById(R.id.back);
	
	 b.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Intent i = new Intent(ViewDonorLocD.this,ViewDonorLocD.class);
			i.putExtra("name",name);
			i.putExtra("sname",sname);
			i.putExtra("address",address);
			startActivity(i);
		}
	});
	
	
	AlertDialog.Builder dialog = new AlertDialog.Builder(this);
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
                 startActivity(anotherActivityIntent); 

		}
	});
	dialog.show();	
}

@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	//    	super.onBackPressed();
}

public void onClick(View v) 
{
	
}
}
