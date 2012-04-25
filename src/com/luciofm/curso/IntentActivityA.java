package com.luciofm.curso;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntentActivityA extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent1);

		Button button = findById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent web = new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://luciofm.com"));
				startActivity(web);
			}
		});
	}
}
