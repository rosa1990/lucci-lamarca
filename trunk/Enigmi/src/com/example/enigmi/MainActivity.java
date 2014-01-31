package com.example.enigmi;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
    private AlertDialog quitDialog;
    private Button exit,select,continua;
    static final int QUIT_DIALOG_ID = 1;
    private SharedPreferences sharedPreferences;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        
        exit = (Button) findViewById (R.id.button3);
        select = (Button) findViewById (R.id.button2);
        continua = (Button) findViewById (R.id.button1);

        
        //Quit Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.Fine)
               .setCancelable(false)
               .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                           MainActivity.this.finish();
                   }
               })
               .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                   }
               });
        quitDialog = builder.create();
        
        //Button3 action
        exit.setOnClickListener(new View.OnClickListener() {
				@Override
                public void onClick(View v) {
	                // mostra il quit dialog
                	quitDialog.show();
	               
                }
        });
        //Button3 action
        select.setOnClickListener(new View.OnClickListener() {
				@Override
                public void onClick(View v) {
					startActivity(new Intent(MainActivity.this,SelectLV.class)); 
				}
        });
        //Button3 action
        continua.setOnClickListener(new View.OnClickListener() {
				@Override
                public void onClick(View v) {

					int lastLevel=sharedPreferences.getInt("LV_complete", -1);
					
					Intent i;
					switch (lastLevel) {
					case 0:
						i= new Intent(MainActivity.this,Livello1Activity.class);
			     		startActivity(i);
			     		break;
			        case 1:
			        	i= new Intent(MainActivity.this,Livello2Activity.class);
			     		startActivity(i);
			     		break;
			        case 2:
				        i= new Intent(MainActivity.this,Livello3Activity.class);
				     	startActivity(i);
				   		break;
				   	default:
			    		i= new Intent(MainActivity.this,Livello0Activity.class);
			     		startActivity(i);
			     		break;
						}
					}
        });
        

    }
    @Override
    protected Dialog onCreateDialog(int id) {
                switch (id) {
                case QUIT_DIALOG_ID:
                        return quitDialog;              
        }
        return null;
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
   	 AlertDialog.Builder builder = new AlertDialog.Builder(this);
     builder.setMessage(R.string.Reset)
            .setCancelable(false)
            .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
            		savePreferences("LV_complete", -1);
                }
            })
            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                     dialog.cancel();
                }
            });
            AlertDialog resetDialog = builder.create();
            resetDialog.show();
	return true;
    	
    }
    private void savePreferences(String key, int value) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor = sharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}
    
}
