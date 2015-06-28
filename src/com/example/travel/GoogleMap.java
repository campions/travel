package com.example.travel;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class GoogleMap extends MapActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_places);
		MapView mapView = (MapView) findViewById(R.id.mapView);
		mapView.setBuiltInZoomControls(true);
		MapController mc = mapView.getController();
		Intent i = getIntent();

		String user_latitude = i.getStringExtra("user_latitude");
		String user_longitude = i.getStringExtra("user_longitude");
		double lat = Double.parseDouble(user_latitude); // latitude
		double lon = Double.parseDouble(user_longitude); // longitude
		GeoPoint geoPoint = new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));
		mc.animateTo(geoPoint);
		mc.setZoom(15);
		mapView.invalidate();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}