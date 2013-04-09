/***************************************************
 **				MSU2U Copyright (c) 2012		  **	
 **		Property of Midwerstern State University  **
 **				Computer Science Dept. 			  **
 ** ************************************************/ 

// This class manages the events activity/screen 
package com.android.msu2u;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TabHost;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.android.msu2u.adapters.TabsAdapter;
import com.android.msu2u.loaders.FragmentStackSupport;
import com.android.msu2u.loaders.CustomEventsLoader;


public class Events extends SherlockFragmentActivity  {
	TabHost mTabHost;
    ViewPager  mViewPager;
    TabsAdapter mTabsAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
    	//I can Change the Theme 
    	super.onCreate(savedInstanceState);
    	
    	setContentView(R.layout.fragment_tabs_pager);
    	mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();
        
        mViewPager = (ViewPager)findViewById(R.id.pager);
        
        mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager);
        
        mTabsAdapter.addTab(mTabHost.newTabSpec("simple").setIndicator("News", getResources().getDrawable(R.drawable.ic_news)),
                FragmentStackSupport.CountingFragment.class, null);
        mTabsAdapter.addTab(mTabHost.newTabSpec("simple").setIndicator("Sports"),
                FragmentStackSupport.CountingFragment.class, null);
        mTabsAdapter.addTab(mTabHost.newTabSpec("simple").setIndicator("Events"),
                CustomEventsLoader.AppListFragment.class, null);
        
        /*mTabsAdapter.addTab(mTabHost.newTabSpec("contacts").setIndicator("Sports"),
                LoaderCursorSupport.CursorLoaderListFragment.class, null);
        mTabsAdapter.addTab(mTabHost.newTabSpec("custom").setIndicator("Events"),
                LoaderCustomSupport.AppListFragment.class, null);
        mTabsAdapter.addTab(mTabHost.newTabSpec("throttle").setIndicator("Throttle"),
                LoaderThrottleSupport.ThrottledLoaderListFragment.class, null); */
        
        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }
        
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tab", mTabHost.getCurrentTabTag());
    }
}