package com.luciofm.curso;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.luciofm.curso.data.TwitterPost;

public class WebServiceJsonActivityA extends ListActivity {
	TextView textView;
	ProgressDialog progress;
	TwitterAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String url = "http://api.twitter.com/1/statuses/public_timeline.json";
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
	
	AsyncTask<String, Void, List<TwitterPost>> httpRequest = new AsyncTask<String, Void, List<TwitterPost>>() {

		@Override
		protected List<TwitterPost> doInBackground(String... params) {
			String url = params[0];
			String response;
			
			try {
				response = getHttpRequest(url);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			ArrayList<TwitterPost> posts = new ArrayList<TwitterPost>();
			try {
				JSONArray array = (JSONArray) new JSONTokener(response).nextValue();
				for (int i = 0; i < array.length(); i++) {
					JSONObject tweet = array.getJSONObject(i);
					JSONObject user = tweet.getJSONObject("user");
					posts.add(new TwitterPost(user.getString("screen_name"), tweet.getString("text")));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			return posts;
		}

		@Override
		protected void onPreExecute() {
			progress = ProgressDialog.show(WebServiceJsonActivityA.this, "Curso", "Carregando...");
		}

		@Override
		protected void onPostExecute(List<TwitterPost> result) {
			progress.dismiss();

			adapter = new TwitterAdapter(WebServiceJsonActivityA.this, android.R.id.text1, result);
			getListView().setAdapter(adapter);
		}

	};

	public class TwitterAdapter extends ArrayAdapter<TwitterPost> {

		LayoutInflater inflater;

		public TwitterAdapter(Context context, int textViewResourceId,
				List<TwitterPost> objects) {
			super(context, textViewResourceId, objects);
			inflater = LayoutInflater.from(WebServiceJsonActivityA.this);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;

			if (v == null)
				v = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);

			TwitterPost post = getItem(position);
			TextView text1 = (TextView) v.findViewById(android.R.id.text1);
			TextView text2 = (TextView) v.findViewById(android.R.id.text2);

			text1.setText(post.getUser());
			text2.setText(post.getText());

			return v;
		}		
	}
}
