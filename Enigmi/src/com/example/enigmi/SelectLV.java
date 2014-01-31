package com.example.enigmi;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import android.view.Menu;
import android.view.View;


public class SelectLV extends Activity {

	 private SharedPreferences sharedPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_lv);
		
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		GridView gridview = (GridView) findViewById(R.id.LvGrid);
	    gridview.setAdapter(new ImageAdapter(this));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	           // Toast.makeText(SelectLV.this, "" + position, Toast.LENGTH_SHORT).show();
	        	int lastLevel=sharedPreferences.getInt("LV_complete", 0);
	        	switch (position) {
                case 0: 
                	if(lastLevel>=position){
                    	startActivity(new Intent(SelectLV.this,Livello1Activity.class));
                	}else Toast.makeText(SelectLV.this, "Devi ancora sbloccarlo!", Toast.LENGTH_SHORT).show();
                	break;
                case 1: 
                	if(lastLevel>=position){
                    	startActivity(new Intent(SelectLV.this,Livello2Activity.class));
                	}else Toast.makeText(SelectLV.this, "Devi ancora sbloccarlo!", Toast.LENGTH_SHORT).show();
                	break;
                case 2: 
                	if(lastLevel>=position){
                    	startActivity(new Intent(SelectLV.this,Livello3Activity.class));
                	}else Toast.makeText(SelectLV.this, "Devi ancora sbloccarlo!", Toast.LENGTH_SHORT).show();
                	break;
                default: 
                	Toast.makeText(SelectLV.this, "Work in progress", Toast.LENGTH_SHORT).show();
	        	break;
	        	}
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_lv, menu);
		return false;
	}

}
