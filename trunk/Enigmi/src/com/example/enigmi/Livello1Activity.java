package com.example.enigmi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Livello1Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_livello1);
		
		EditText testo = (EditText) findViewById (R.id.editText1);
		View next = (View) findViewById (R.id.imageView1);
		TextView testo2 = (TextView) findViewById (R.id.textView1);
		
		//TODO far sparire testo2 e next
		String input=testo.getText().toString();
		//TODO controllare che get prende veramente il testo
		if(input.equals("LIVELLO2")||input.equals("livello2")){
			//TODO far riapparire testo2 e next
		}
        next.setOnClickListener(new View.OnClickListener() {
				@Override
                public void onClick(View v) {
                    // Iniziamo con il primo livello
					startActivity(new Intent(Livello1Activity.this,Livello2Activity.class)); 
                }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.livello1, menu);
		return true;
	}

}
