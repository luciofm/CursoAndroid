package com.luciofm.curso;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class CursoActivity extends Activity {

	String[] activities = {
			"FrameLayoutActivity",
			"RelativeLayoutActivity",
			"LinearLayoutActivityA",
			"LinearLayoutActivityB",
			"IntentActivityA",
			"IntentActivityB",
			"IntentActivityC",
			"ListViewActivityA",
			"ListViewActivityB",
			"HttpRequestActivityA",
			"HttpRequestActivityB",
			"HttpRequestActivityC",
			"HttpRequestActivityD",
			"WebServiceJsonActivityA",
			"WebServiceJsonActivityB"
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        LinearLayout container = (LinearLayout) findViewById(R.id.buttons_container);
        for (final String activity : activities) {
        	Button btn = new Button(this);
        	btn.setText(activity);
        	btn.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClassName(getPackageName(), getPackageName() + "." + activity);
					startActivity(intent);
				}
			});
        	container.addView(btn);
        }
    }
}