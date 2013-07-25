package com.luciofm.curso;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

public class ActivityView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);

		WebView webView = (WebView) findViewById(R.id.webview);

		if (Intent.ACTION_VIEW.contentEquals(getIntent().getAction())) {
			Uri url = getIntent().getData();
			webView.loadUrl(url.toString());
		}
	}

}
