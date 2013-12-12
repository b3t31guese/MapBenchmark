package com.eu.dobrev.android.map.benchmark;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.util.Log;

public class NativeMapFragment extends MapFragment implements ITestableMapFragment {

	@Override
	public void clearMap() {
		this.getMap().clear();
		
	}

	@Override
	public void addMarkers(int count) {
		LatLng[] randomPositionsOnScreen = generateRandomPositionsInCurrentScreenBounds(count);
		
		long startTime = System.currentTimeMillis();
		GoogleMap map = this.getMap();
		for (int i = 0; i < count; i++) {
			map.addMarker(new MarkerOptions().position(randomPositionsOnScreen[i]));
		}
		long endTime = System.currentTimeMillis();
				
		Log.d("MB", "Start time is: " + startTime);
		Log.d("MB", "End time is: " + endTime);
		Log.d("MB", "Runtime is: " + (endTime - startTime));
	}
	
	/**
	 * Generate number of random positions on the current map screen
	 * 
	 * Only positions inside the visible map bounds are generated
	 * 
	 * @param count
	 * @return
	 */
	private LatLng[] generateRandomPositionsInCurrentScreenBounds(int count) {
		
		LatLngBounds bounds = this.getMap().getProjection().getVisibleRegion().latLngBounds;
		LatLng[] randomPositionsOnScreen = new LatLng[count];
		
		double minLat = Math.min(bounds.northeast.latitude, bounds.southwest.latitude);
		double maxLat = Math.max(bounds.northeast.latitude, bounds.southwest.latitude);

		double minLng = Math.min(bounds.northeast.longitude, bounds.southwest.longitude);
		double maxLng = Math.max(bounds.northeast.longitude, bounds.southwest.longitude);
		
		double latDifference = maxLat - minLat;
		double lngDifference = maxLng - minLng;
		
		int index = 0;
		while (index < count) {
			double lat = minLat + Math.random() * latDifference;
			double lng = minLng + Math.random() * lngDifference;
			LatLng randomLatLng = new LatLng(lat, lng);	
			randomPositionsOnScreen[index] = randomLatLng;
			index++;
		};
		
		return randomPositionsOnScreen;
	}
	
	

	@Override
	public void addLines(int count) {
		LatLng[] randomPositionsOnScreen = generateRandomPositionsInCurrentScreenBounds(count * 2);
		GoogleMap map = this.getMap();
		for (int i = 0; i < count; i+=2) {
			map.addPolyline(new PolylineOptions()
				.add(randomPositionsOnScreen[i], randomPositionsOnScreen[i + 1])
			);
		}
	}
}
