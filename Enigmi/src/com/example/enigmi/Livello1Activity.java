package com.example.enigmi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Livello1Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_livello1);
		
		//se supero livello passa al livello 2
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.livello1, menu);
		return true;
	}

}
