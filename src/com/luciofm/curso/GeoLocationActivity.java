package com.luciofm.curso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GeoLocationActivity extends ListActivity {

	LocationManager locationManager;

	ListView listView;
	LocationAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* Começa a adicinar as views de baixo para cima, para a mais nova ser sempre a primeira */
		getListView().setStackFromBottom(true);
		mAdapter = new LocationAdapter(this, new ArrayList<Location>());
		setListAdapter(mAdapter);

		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		Location location = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location != null)
			mAdapter.add(location);
	}

	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(locationListener);
	}

	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				5000, 1, locationListener);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	LocationListener locationListener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onLocationChanged(Location location) {
			mAdapter.add(location);
		}
	};

	public class LocationAdapter extends ArrayAdapter<Location> {

		LayoutInflater inflater;

		public LocationAdapter(Context context, List<Location> objects) {
			super(context, android.R.layout.simple_expandable_list_item_2,
					android.R.id.text1, objects);
			inflater = LayoutInflater.from(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = super.getView(position, convertView, parent);

			Location loc = getItem(position);
			((TextView) v.findViewById(android.R.id.text1)).setTextSize(14f);
			((TextView) v.findViewById(android.R.id.text1)).setText(loc
					.getLatitude()
					+ ", "
					+ loc.getLongitude()
					+ " - accuracy: " + loc.getAccuracy());

			((TextView) v.findViewById(android.R.id.text2)).setText(new Date(
					loc.getTime()).toString());

			return v;
		}

	}
}
