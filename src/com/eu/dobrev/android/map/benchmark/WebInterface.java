package com.eu.dobrev.android.map.benchmark;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import android.util.Log;

public class WebInterface {

	Context context;
	
	public WebInterface(Context c) {
		this.context = c;
	}
	
	@JavascriptInterface
	public void showToast(String toast) {
		Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
	}
	
	@JavascriptInterface
	public void log(String message) {
		Log.d("MB", "Message from JS: " + message);
	}
}
