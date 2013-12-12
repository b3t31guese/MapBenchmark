package com.eu.dobrev.android.map.benchmark;

import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.ActionBar;
import android.app.FragmentTransaction;

public class MainTabListener<T extends Fragment> implements ActionBar.TabListener {
	
	private Fragment fragment;
    private final Activity activity;
    private final String tag;
    private final Class<T> cls;
	
    /** Constructor used each time a new tab is created.
     * @param activity  The host Activity, used to instantiate the fragment
     * @param tag  The identifier tag for the fragment
     * @param cls  The fragment's Class, used to instantiate the fragment
     */
    public MainTabListener(Activity activity, String tag, Class<T> cls) {
       	this.activity = activity;
       	this.tag = tag;
       	this.cls = cls;
   	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// DO NOTHING HERE...
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// Check if the fragment is already initialized
        if (fragment == null) {
            // If not, instantiate and add it to the activity
            fragment = Fragment.instantiate(activity, cls.getName());
            ft.add(android.R.id.content, fragment, tag);
        } else {
            // If it exists, simply attach it in order to show it
            ft.attach(fragment);
        }
        
        ((MainActivity) activity).setCurrentFragment((ITestableMapFragment) fragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		if (fragment != null) {
            // Detach the fragment, because another one is being attached
            ft.detach(fragment);
        }
	}
}