package com.example.enigmi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class ContinuaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_continua);
		
		
		//TODO prendere ultimo livello dal file
		int lastLevel=(int) (Math.random()*1);
		
		Intent i;
		switch (lastLevel) {
         case 0:
        	  i= new Intent(ContinuaActivity.this,Livello1Activity.class);
     		 startActivity(i);
         case 1:
        	 i= new Intent(ContinuaActivity.this,Livello2Activity.class);
     		 startActivity(i);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.continua, menu);
		return true;
	}
 
}
