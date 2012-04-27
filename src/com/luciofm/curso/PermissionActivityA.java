package com.luciofm.curso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PermissionActivityA extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent1);

		Button button = findById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String filename = "/sdcard/filename.txt";
				File file = new File(filename);
				FileOutputStream fos;
				byte[] data = new String("data to write to file").getBytes();
				try {
					fos = new FileOutputStream(file);
					fos.write(data);
					fos.flush();
					fos.close();
				} catch (FileNotFoundException e) {
					Log.d("Curso", "FileNotFoundException: " + e.getMessage());
				} catch (IOException e) {
					Log.d("Curso", "IOException: " + e.getMessage());
				}
			}
		});
	}
}
