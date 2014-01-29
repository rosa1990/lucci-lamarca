package com.example.enigmi;


import android.os.Bundle;
import android.view.Menu;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;


public class Livello2Activity extends Activity {
	 private AlertDialog pauseDialog;
     
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_livello2);
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
        
        pauseDialog = builder.create();
       pauseDialog.show();
       
   	 } 
   
	
}
