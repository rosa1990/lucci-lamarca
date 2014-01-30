package com.example.enigmi;


import java.io.IOException;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;


public class Livello2Activity extends Activity {
	
    private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;

   
    private MediaRecorder mRecorder = null;

	 final static int RQS_RECORDING=1;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_livello2);
		
		//Intent i=new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
	  //  startActivityForResult(i,RQS_RECORDING);
	     startRecording();
	    if (mRecorder != null) {
	    	  stopRecording();
	            mRecorder = null;
	       }
	  
	}

	private void startRecording() {
		if (mRecorder != null) {
			
			      mRecorder.release();
			
			   }

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
	        
	    startActivity(new Intent(Livello2Activity.this,Livello1Activity.class)); 
	    
	}

	
	
	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent Data){
		if (requestCode==RQS_RECORDING)
			Toast.makeText(Livello2Activity.this, "preso audio", Toast.LENGTH_LONG).show();
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
   
	
}
