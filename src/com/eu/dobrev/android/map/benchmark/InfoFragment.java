package com.eu.dobrev.android.map.benchmark;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;

public class InfoFragment extends Fragment implements ITestableMapFragment {
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.info_fragment, null);
		//return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void clearMap() {
		Log.d("MB", "Nothing to clear!");
	}

	@Override
	public void addMarkers(int count) {
		Log.d("MB", "Nothing to add in the info window!");
	}

	@Override
	public void addLines(int count) {
		Log.d("MB", "Nothing to add in the info window!");
	}
}
