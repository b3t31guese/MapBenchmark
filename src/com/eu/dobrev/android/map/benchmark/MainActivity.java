package com.eu.dobrev.android.map.benchmark;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private ITestableMapFragment currentFragment;
	
	private String[] overlayTypes = new String[] {"Markers", "Polylines"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(android.R.id.content);
		
		final ActionBar bar = getActionBar();
		/** An array of strings to populate dropdown list */
	    
		
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		
		bar.addTab(bar.newTab().setText("Info")
				.setTabListener(new MainTabListener<InfoFragment>(this, "info", InfoFragment.class)));
		
		bar.addTab(bar.newTab().setText("Native Map").setTabListener(
				new MainTabListener<NativeMapFragment>(this, "native", NativeMapFragment.class)));
		
		bar.addTab(bar.newTab().setText("HTML Map").setTabListener(
				new MainTabListener<WebViewMapFragment>(this, "html", WebViewMapFragment.class)));
		
		
		if (savedInstanceState != null) {
            bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
			case R.id.clear_markers:
				getCurrentFragment().clearMap();
				return true;
			case R.id.add_markers:
				invokeAddMarkers();
				return true;
			case R.id.add_lines:
				invokeAddLines();
				return true;
			default: 
				return super.onOptionsItemSelected(item);
		}
	}

	private void invokeAddLines() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    // Get the layout inflater
	    LayoutInflater inflater = getLayoutInflater();

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    
	    View v = inflater.inflate(R.layout.add_dialog, null);
	    
	    final EditText countTxt =  (EditText) v.findViewById(R.id.count); 
	    
	    builder.setView(v)
	    // Add action buttons
	           .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   try {
	                	   int count = Integer.parseInt(countTxt.getText().toString());
	                	   if (count > 0 && count < 101) {
	                		   getCurrentFragment().addLines(count);
	                	   } else {
	                		   Toast.makeText(getApplicationContext(), "Invalid number!", Toast.LENGTH_SHORT).show();
	                	   }
	                   } catch (Exception ex) {
	                	   Toast.makeText(getApplicationContext(), "Invalid number!", Toast.LENGTH_SHORT).show();
	                   }
	               }
	           })
	           .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   
	               }
	           })
	           .setTitle(R.string.add_lines_dialog_title);      
	    builder.create().show();
	}

	private void invokeAddMarkers() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    // Get the layout inflater
	    LayoutInflater inflater = getLayoutInflater();

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    
	    View v = inflater.inflate(R.layout.add_dialog, null);
	    
	    final EditText countTxt =  (EditText) v.findViewById(R.id.count); 
	    
	    builder.setView(v)
	    // Add action buttons
	           .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   try {
	                	   int count = Integer.parseInt(countTxt.getText().toString());
	                	   if (count > 0 && count < 1001) {
	                		   getCurrentFragment().addMarkers(count);
	                	   } else {
	                		   Toast.makeText(getApplicationContext(), "Invalid number!", Toast.LENGTH_SHORT).show();
	                	   }
	                   } catch (Exception ex) {
	                	   Toast.makeText(getApplicationContext(), "Invalid number!", Toast.LENGTH_SHORT).show();
	                   }
	               }
	           })
	           .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   
	               }
	           })
	           .setTitle(R.string.add_markers_dialog_title);      
	    builder.create().show();
		
	}

	public ITestableMapFragment getCurrentFragment() {
		return currentFragment;
	}

	public void setCurrentFragment(ITestableMapFragment currentFragment) {
		this.currentFragment = currentFragment;
	} 
}