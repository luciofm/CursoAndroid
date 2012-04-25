package com.luciofm.curso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResponseActivity extends BaseActivity {

	public static final String NOME = "NOME";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent2);

		Button button = findById(R.id.button1);
		final EditText text = findById(R.id.editText1);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (text.getText().length() == 0) {
					Toast.makeText(ResponseActivity.this, "Preencha o campo...", Toast.LENGTH_LONG).show();
					return;
				}
				String nome = text.getText().toString();
				Intent response = new Intent();
				response.putExtra(NOME, nome);
				setResult(RESULT_OK, response);
				finish();
			}
		});
	}
}