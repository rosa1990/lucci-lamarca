package com.example.enigmi;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private AlertDialog quitDialog;
	private Button exit,select,continua;
	static final int QUIT_DIALOG_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        exit = (Button) findViewById (R.id.button3);
        select = (Button) findViewById (R.id.button2);
        continua = (Button) findViewById (R.id.button1);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Sei sicuro di voler uscire?")
               .setCancelable(false)
               .setPositiveButton("Si'", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   MainActivity.this.finish();
                   }
               })
               .setNegativeButton("No", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                   }
               });
        quitDialog = builder.create();
        exit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(QUIT_DIALOG_ID);
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
    

    
}
