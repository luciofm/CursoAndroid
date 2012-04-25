package com.luciofm.curso;

import android.app.Activity;

public class BaseActivity extends Activity {

	@SuppressWarnings("unchecked")
	public <T> T findById(int id) {
		return (T) findViewById(id);
	}

}
