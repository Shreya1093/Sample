package com.example.fooddonation;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class OrphanageViewVolunteer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orphanage_view_volunteer);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.orphanage_view_volunteer, menu);
		return true;
	}

}
