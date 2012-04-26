package com.luciofm.curso;

import android.app.Activity;
import android.content.Intent;

public class BackPressedActivity extends Activity {

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, CursoActivity.class);
		startActivity(intent);
		finish();
	}
}
