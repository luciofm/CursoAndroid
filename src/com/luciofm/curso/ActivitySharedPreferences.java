package com.luciofm.curso;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivitySharedPreferences extends Activity {

	TextView text;
	EditText edit;
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sharedpref);

		text = (TextView) findViewById(R.id.textView1);
		edit = (EditText) findViewById(R.id.editText1);
		button = (Button) findViewById(R.id.button1);

		final SharedPreferences prefs = getSharedPreferences("prefs.xml", Context.MODE_PRIVATE);

		if (prefs.getBoolean("FIRST_RUN", true)) {
			text.setText("First run...");
			prefs.edit().putBoolean("FIRST_RUN", false).commit();
		} else {
			text.setText("Obrigado por retornar...");
		}

		if (!TextUtils.isEmpty(prefs.getString("USERNAME", null))) {
			edit.setText(prefs.getString("USERNAME", null));
		}

		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				prefs.edit().putString("USERNAME", edit.getText().toString()).commit();
			}
		});
	}

}
