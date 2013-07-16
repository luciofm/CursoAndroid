package com.luciofm.curso;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class BackgroundActivityD extends Activity {

	TextView textView;
	ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_response_text);

		textView = (TextView) findViewById(R.id.text);
		String url = "http://goo.gl/AU30Z";
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

	AsyncTask<String, Integer, String> httpRequest = new AsyncTask<String, Integer, String>() {

		@Override
		protected String doInBackground(String... params) {
			String url = params[0];

			
			try {
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(url);
				HttpResponse responseGet = client.execute(get);
				HttpEntity entity = responseGet.getEntity();
				long bytesTotal = entity.getContentLength();
				InputStream is = entity.getContent();
				BufferedInputStream bis = new BufferedInputStream(is);
				File tempFile = new File("/mnt/sdcard", "file.tmp");
				FileOutputStream fos = new FileOutputStream(tempFile, true);

                long bytesReaded = 0;

				byte[] buffer = new byte[16384];
                int read = is.read(buffer);

                while (read > 0) {
                        fos.write(buffer.clone(), 0, read);
                        buffer = new byte[16384];
                        bytesReaded += read;

                        double dbReaded = (double) bytesReaded;
                        double dbTotal = (double) bytesTotal;
                        int percent = (int) Math.floor((dbReaded / dbTotal) * 100);
                        publishProgress(percent);
                        read = is.read(buffer);
                }
                bis.close();
                fos.close();

                return tempFile.getAbsolutePath();
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
		protected void onPreExecute() {
			progress = new ProgressDialog(BackgroundActivityD.this);
			progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progress.setIndeterminate(false);
			progress.setTitle("Curso");
			progress.setMessage("Baixando...");
			progress.setCancelable(false);
			progress.setCanceledOnTouchOutside(false);
			progress.setProgress(0);
			progress.show();
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != null)
				textView.setText(Html.fromHtml(result));
			else
				textView.setText("Erro carregando dados");
			progress.dismiss();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			progress.setProgress(values[0]);
		}

	};
}
