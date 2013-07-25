package com.luciofm.curso;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class IntentActivityB extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent_action);

		final EditText edit = findById(R.id.editText1);
		Button button = findById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent sharingIntent = new Intent(Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				String share = edit.getText().toString();
				if (TextUtils.isEmpty(share))
					share = "Compartilhando via Intent no Android";
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
						share);
				startActivity(Intent.createChooser(sharingIntent, "Compartilhe com"));
			}
		});

		Button view = findById(R.id.button2);
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(edit.getText().toString()))
					return;
				String url = edit.getText().toString();
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(intent);
			}
		});
	}
}
