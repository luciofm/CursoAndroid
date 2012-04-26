package com.luciofm.curso;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class IntentPhotoActivity extends BaseActivity {

	
	protected static final int PHOTO_ACTIVITY = 2;

	ImageView mImageView;
	private Bitmap mImageBitmap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent_photo);

		Button button = findById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(photo, PHOTO_ACTIVITY);
			}
		});
		mImageView = findById(R.id.imageView1);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PHOTO_ACTIVITY && resultCode == RESULT_OK)
			handleSmallCameraPhoto(data);
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void handleSmallCameraPhoto(Intent intent) {
	    Bundle extras = intent.getExtras();
	    mImageBitmap = (Bitmap) extras.get("data");
	    mImageView.setImageBitmap(mImageBitmap);
	}
}
