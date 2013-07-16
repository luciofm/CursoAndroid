package com.luciofm.curso;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luciofm.curso.data.Gist;

public class WebServiceJsonActivityB extends ListActivity {
	TextView textView;
	ProgressDialog progress;
	GistAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

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
	
	AsyncTask<String, Void, ArrayList<Gist>> httpRequest = new AsyncTask<String, Void, ArrayList<Gist>>() {

		@Override
		protected ArrayList<Gist> doInBackground(String... params) {
			String url = params[0];
			String response;
			
			try {
				response = getHttpRequest(url);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			//Type type = new TypeToken<ArrayList<Gist>>(){}.getType();
			Type type = new TypeToken<ArrayList<Gist>>(){}.getType();
			ArrayList<Gist> gists = new Gson().fromJson(response, type);
			return gists;
		}

		@Override
		protected void onPreExecute() {
			progress = ProgressDialog.show(WebServiceJsonActivityB.this, "Curso", "Carregando...");
		}

		@Override
		protected void onPostExecute(ArrayList<Gist> result) {
			progress.dismiss();

			adapter = new GistAdapter(WebServiceJsonActivityB.this, android.R.id.text1, result);
			getListView().setAdapter(adapter);
		}
		
	};

	public class GistAdapter extends ArrayAdapter<Gist> {

		LayoutInflater inflater;

		public GistAdapter(Context context, int textViewResourceId,
				List<Gist> objects) {
			super(context, textViewResourceId, objects);
			inflater = LayoutInflater.from(WebServiceJsonActivityB.this);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;

			if (v == null)
				v = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);

			Gist gist = getItem(position);
			TextView text1 = (TextView) v.findViewById(android.R.id.text1);
			TextView text2 = (TextView) v.findViewById(android.R.id.text2);

			text1.setText(gist.getUser().getLogin());
			text2.setText(gist.getDescription());

			return v;
		}		
	}
}
