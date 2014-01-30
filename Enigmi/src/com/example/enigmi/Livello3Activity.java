package com.example.enigmi;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Livello3Activity extends Activity implements SensorEventListener{
	
	private SensorManager sensorManager;
	private ImageView giorno_notte;
	private Button b;
	private EditText editText;
	private TextView help,help1;
	private String input=null;
	private Boolean helpMenu=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_livello3);
		
		giorno_notte = (ImageView) findViewById (R.id.imageLv3);
		editText = (EditText) findViewById (R.id.editTextLv3);
		b=(Button) findViewById (R.id.lv3button);
		help = (TextView)findViewById (R.id.textViewLv3);
		help.setVisibility(View.INVISIBLE);
		help1 = (TextView)findViewById (R.id.textViewLv3_1);
		help1.setVisibility(View.INVISIBLE);
		
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor( Sensor.TYPE_LIGHT ),SensorManager.SENSOR_DELAY_NORMAL );
		Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		if (sensor == null) {
			helpMenu=true;
			help1.setVisibility(View.VISIBLE);
		}

		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				input = editText.getText().toString();
				if(input.equals("LOVE")||input.equals("love")){
					startActivity(new Intent(Livello3Activity.this,Livello0Activity.class));
				}else{
					help.setVisibility(View.VISIBLE);
					help.setText(R.string.help3);
				}
			}
		});
		
	}
	
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            //il sensore di luce ha cambiato stato
            float luminosità=event.values[0];
            if(luminosità<50){
                //è buio
                giorno_notte.setImageResource(R.drawable.lv3_b);
            }
            else{
                //non è buio
                giorno_notte.setImageResource(R.drawable.lv3_a);
                giorno_notte.setBackgroundResource(R.drawable.lv3_a);
            }
        }
    }  
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.livello3, menu);
		if(helpMenu)return true;
		else return false;
	}

	@Override
    public void onBackPressed() {
        pause();
    }
    protected void pause(){
   	 AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.Pausa)
               .setCancelable(false)
               .setPositiveButton(R.string.TornaMenu, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	 Intent i= new Intent(Livello3Activity.this,MainActivity.class);
                     i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(i);
                   }
               })
               .setNegativeButton(R.string.annulla, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {   
                        dialog.cancel();
                       	
                   }
               });
       AlertDialog pauseDialog = builder.create();
       pauseDialog.show();
       
   	 } 
}
