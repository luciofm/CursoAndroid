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
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BackgroundActivityB extends Activity {

	TextView textView;
	ProgressBar progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_response_text_progress);

		textView = (TextView) findViewById(R.id.text);
		progress = (ProgressBar) findViewById(R.id.progressBar);
		String url = "https://api.github.com/gists/public";
		httpRequest.execute(url);
	}

	public static String getHttpRequest(String urlAddress)
			throws ClientProtocolException, IOException, UnknownHostException {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(urlAddress);
		HttpResponse responseGet = client.execute(get);
		String entity = EntityUtils.toString(responseGet.getEntity(), "UTF-8");

		return entity;
	}

	AsyncTask<String, Void, String> httpRequest = new AsyncTask<String, Void, String>() {

		@Override
		protected String doInBackground(String... params) {
			String url = params[0];

			
			try {
				return getHttpRequest(url);
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
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != null)
				textView.setText(Html.fromHtml(result));
			else
				textView.setText("Erro carregando dados");
			progress.setVisibility(View.GONE);
		}

	};
}
