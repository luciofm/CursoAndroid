package com.luciofm.curso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivitySend extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_activity);

		if (Intent.ACTION_SEND.contentEquals(getIntent().getAction())) {
			Button ok = (Button) findViewById(R.id.buttonOk);
			Button cancel = (Button) findViewById(R.id.buttonCancel);
			EditText edit = (EditText) findViewById(R.id.editText1);

			edit.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));

			ok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(ActivitySend.this, "Enviando mensagem", Toast.LENGTH_SHORT).show();
					finish();
				}
			});

			cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					finish();
				}
			});
			return;
		}
	}

}
