package com.example.enigmi;


import java.io.IOException;


import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class Livello2Activity extends Activity {
	
  private static final String LOG_TAG = "AudioRecordTest";
    private MediaRecorder mRecorder = null;
	
	Button start,stop;
	ImageView fuoco;
	TextView testo;
	double amplitude;
	static final private double EMA_FILTER = 0.6;
	 private double mEMA = 0.0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_livello2);
		testo = (TextView) findViewById (R.id.textViewLv2);
		fuoco = (ImageView) findViewById (R.id.imageViewLv2);
		start = (Button) findViewById (R.id.button2_lv2);
		start.setOnClickListener(new View.OnClickListener() {
			@Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
				startRecording(); 
           
			}
    });
		
		stop = (Button) findViewById (R.id.button1_lv2);
		stop.setOnClickListener(new View.OnClickListener() {
			@Override
            public void onClick(View v) {			
				if (mRecorder ==null)
					Toast.makeText(Livello2Activity.this, R.string.ritenta, Toast.LENGTH_LONG).show();
				else 
					stopRecording();
			}
    });
		
	
	}
	

	private void startRecording() {
		if (mRecorder == null) {

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
       String audio_file = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio_recording.3gp";
        Log.d("FILE PATH ORIGINAL", audio_file);
        mRecorder.setOutputFile(audio_file);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        mRecorder.getMaxAmplitude();
        mRecorder.start();
        amplitude = getAmplitudeEMA();
       
		}
    }

    private void stopRecording() {
    	
    	if (mRecorder != null) {
    		 amplitude = getAmplitudeEMA();
    	       
            mRecorder.stop();   
            mRecorder.release();
            mRecorder = null;
    }
        if (amplitude>0 && amplitude<2){
			  mRecorder = null;
			  start.setVisibility(View.INVISIBLE);
			  stop.setVisibility(View.INVISIBLE);
			  testo.setVisibility(View.INVISIBLE);
			  
			  fuoco.setImageResource(R.drawable.next2);
			  fuoco.setClickable(true);
			  fuoco.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					savePreferences("LV_complete", 2);
					startActivity(new Intent(Livello2Activity.this,Livello3Activity.class));
				}
			});
		}else if (amplitude>2){
			Toast.makeText(Livello2Activity.this, R.string.help_LV2_2, Toast.LENGTH_LONG).show();
		}else Toast.makeText(Livello2Activity.this, R.string.help_LV2_3, Toast.LENGTH_LONG).show();

			  
       
	}

    public double getAmplitude() {
        if (mRecorder != null)
                return  (mRecorder.getMaxAmplitude()/2700.0);
        else
                return 0;

}

    public double getAmplitudeEMA() {
        double amp = getAmplitude();
        mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;
        return mEMA;
}

    
    
    
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.livello2, menu);
		return false;
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
                	 Intent i= new Intent(Livello2Activity.this,MainActivity.class);
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
    private void savePreferences(String key, int value) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor = sharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
}
