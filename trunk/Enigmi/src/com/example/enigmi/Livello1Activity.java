package com.example.enigmi;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Livello1Activity extends Activity {

	private EditText editText;
	private View next;
	private TextView testo2;
	private Button b;
	private String input=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_livello1);
		
		editText = (EditText) findViewById (R.id.editText1);
		next = (View) findViewById (R.id.imageView1);
		testo2 = (TextView) findViewById (R.id.textView2);
		b=(Button) findViewById (R.id.lv1button);
		
		testo2.setVisibility(View.INVISIBLE);
		next.setVisibility(View.INVISIBLE);

		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				input = editText.getText().toString();
				if(input.equals("LIVELLO2")||input.equals("livello2")||input.equals("LIVELLO 2")||input.equals("livello 2")){
					testo2.setVisibility(View.VISIBLE);
					testo2.setText(R.string.Lv1_2);
					next.setVisibility(View.VISIBLE);
				}else{
					testo2.setVisibility(View.VISIBLE);
					testo2.setText(R.string.help1);
				}
			}
		});
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
                	 Intent i= new Intent(Livello1Activity.this,MainActivity.class);
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
