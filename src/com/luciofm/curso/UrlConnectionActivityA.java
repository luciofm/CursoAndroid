package com.luciofm.curso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class UrlConnectionActivityA extends Activity {

	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_response_text);

		textView = (TextView) findViewById(R.id.text);
		String url = "https://api.github.com/gists/public";
		try {
			String response = getHttpRequest(url);
			textView.setText(Html.fromHtml(response));
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

	public static String getHttpRequest(String urlAddress)
			throws ClientProtocolException, IOException, UnknownHostException {
		URL url = new URL(urlAddress);
		URLConnection connection = url.openConnection();

		BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()), 4096);
		String line;
		StringBuilder sb =  new StringBuilder();
		while ((line = rd.readLine()) != null) {
				sb.append(line);
		}
		rd.close();

		return sb.toString();
	}

}
