package com.luciofm.curso;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class IntentActivityC extends BaseActivity {

	private static final int REQUEST_CODE = 1;

	private TextView text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent1);

		Button button = findById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(IntentActivityC.this, ResponseActivity.class);
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
		text = findById(R.id.textView1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE) {
			switch (resultCode) {
			case RESULT_CANCELED:
				Toast.makeText(this, "Ação cancelada", Toast.LENGTH_LONG).show();
				break;
			case RESULT_OK:
				String nome = data.getStringExtra(ResponseActivity.NOME);
				if (!TextUtils.isEmpty(nome))
					text.setText(nome);
			default:
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	
}
