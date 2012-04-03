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
	private Button httpA;
	private Button httpB;
	private Button httpC;
	private Button httpD;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listViewActivityA = (Button) findViewById(R.id.buttonListViewA);
        listViewActivityB = (Button) findViewById(R.id.buttonListViewB);
        httpA = (Button) findViewById(R.id.buttonHttpA);
        httpB = (Button) findViewById(R.id.buttonHttpB);
        httpC = (Button) findViewById(R.id.buttonHttpC);
        httpD = (Button) findViewById(R.id.buttonHttpD);

        listViewActivityA.setOnClickListener(buttonClickListener);
        listViewActivityB.setOnClickListener(buttonClickListener);
        httpA.setOnClickListener(buttonClickListener);
        httpB.setOnClickListener(buttonClickListener);
        httpC.setOnClickListener(buttonClickListener);
        httpD.setOnClickListener(buttonClickListener);
    }

    OnClickListener buttonClickListener = new OnClickListener() {
		
		public void onClick(View v) {
			Intent intent = null;
			if (v == listViewActivityA)
				intent = new Intent(CursoActivity.this, ListViewActivityA.class);
			if (v == listViewActivityB)
				intent = new Intent(CursoActivity.this, ListViewActivityB.class);
			if (v == httpA)
				intent = new Intent(CursoActivity.this, HttpRequestActivityA.class);
			if (v == httpB)
				intent = new Intent(CursoActivity.this, HttpRequestActivityB.class);
			if (v == httpC)
				intent = new Intent(CursoActivity.this, HttpRequestActivityC.class);
			if (v == httpD)
				intent = new Intent(CursoActivity.this, HttpRequestActivityD.class);

			startActivity(intent);
		}
	};
}