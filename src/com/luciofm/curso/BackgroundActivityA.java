package com.luciofm.curso;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BackgroundActivityA extends Activity {

	TextView textView;
	String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_response_text);

		textView = (TextView) findViewById(R.id.text);
		url = "https://api.github.com/gists/public";
		new Thread(runnable).start();
	}

	public static String getHttpRequest(String urlAddress)
			throws ClientProtocolException, IOException, UnknownHostException {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(urlAddress);
		HttpResponse responseGet = client.execute(get);
		String entity = EntityUtils.toString(responseGet.getEntity(), "UTF-8");

		return entity;
	}

	Runnable runnable = new Runnable() {
		
		public void run() {
			try {
				final String response = getHttpRequest(url);
				BackgroundActivityA.this.runOnUiThread(new Runnable() {
					
					public void run() {
						textView.setText(response);
					}
				});
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
}
