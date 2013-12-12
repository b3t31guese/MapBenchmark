package com.eu.dobrev.android.map.benchmark;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewFragment;

public class WebViewMapFragment extends WebViewFragment implements ITestableMapFragment {
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v =  super.onCreateView(inflater, container, savedInstanceState);
		
		WebView webView = this.getWebView();
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		webView.addJavascriptInterface(new WebInterface(this.getActivity()), "Android");	
		webView.loadUrl("file:///android_asset/gmap_benchmark.html");
		
		return v;
	}

	@Override
	public void clearMap() {
		this.getWebView().loadUrl("javascript:clearMap();");
	}

	@Override
	public void addMarkers(int count) {
		this.getWebView().loadUrl("javascript:addMarkers(" + count + ");");
	}

	@Override
	public void addLines(int count) {
		this.getWebView().loadUrl("javascript:addLines(" + count + ");");
	}
}
