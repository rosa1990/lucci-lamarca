package com.example.enigmi;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;

public class Livello0Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_livello0);
		
		View inizia = (View) findViewById (R.id.imageView1);
		
        inizia.setOnClickListener(new View.OnClickListener() {
				@Override
                public void onClick(View v) {
                    // Iniziamo con il primo livello
					savePreferences("LV_complete", 0);
					startActivity(new Intent(Livello0Activity.this,Livello1Activity.class)); 
                }
        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.livello1, menu);
		return false;
	}
    private void savePreferences(String key, int value) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor = sharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

}
