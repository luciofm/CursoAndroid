package com.luciofm.curso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CursoActivity extends Activity {

	private Button listViewActivityA;
	private Button listViewActivityB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listViewActivityA = (Button) findViewById(R.id.buttonListViewA);
        listViewActivityB = (Button) findViewById(R.id.buttonListViewB);

        listViewActivityA.setOnClickListener(buttonClickListener);
        listViewActivityB.setOnClickListener(buttonClickListener);
    }

    OnClickListener buttonClickListener = new OnClickListener() {
		
		public void onClick(View v) {
			Intent intent = null;
			if (v == listViewActivityA)
				intent = new Intent(CursoActivity.this, ListViewActivityA.class);
			if (v == listViewActivityB)
				intent = new Intent(CursoActivity.this, ListViewActivityB.class);

			startActivity(intent);
		}
	};
}