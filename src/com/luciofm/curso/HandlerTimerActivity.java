package com.luciofm.curso;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HandlerTimerActivity extends Activity {
	private Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent_photo);
		button = (Button) findViewById(R.id.button1);
		final Handler handler = new Handler();

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						Toast.makeText(HandlerTimerActivity.this, "Tempo!",
								Toast.LENGTH_SHORT).show();
					}

				}, 5000);
			}

		});
	}

}
