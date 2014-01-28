package com.example.enigmi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import android.view.Menu;
import android.view.View;


public class SelectLV extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_lv);
		
		GridView gridview = (GridView) findViewById(R.id.LvGrid);
	    gridview.setAdapter(new ImageAdapter(this));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	           // Toast.makeText(SelectLV.this, "" + position, Toast.LENGTH_SHORT).show();
	        	switch (position) {
                case 0: 
                	startActivity(new Intent(SelectLV.this,Livello0Activity.class));
                break;
                default: 
                	Toast.makeText(SelectLV.this, "Working in progress", Toast.LENGTH_SHORT).show();
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
