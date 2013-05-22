/***************************************************
 **				MSU2U Copyright (c) 2012		  **	
 **		Property of Midwerstern State University  **
 **				Computer Science Dept. 			  **
 ** ************************************************/ 

// This class manages the campus map activity/screen

package com.android.msu2u;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TabHost;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.android.msu2u.adapters.TabsAdapter;
import com.android.msu2u.loaders.FragmentStackSupport;

public class CampusMap extends SherlockActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_campusmap);
        
        //TextView txtProduct = (TextView) findViewById(R.id.textView1);
        
        Intent i = getIntent();
        // getting attached intent data
        String menu = i.getStringExtra("button");
        // displaying selected button name
        //txtProduct.setText(menu);
	} // end OnCreate
} // end CampusMap Class