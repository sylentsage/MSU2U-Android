/***************************************************
 **				MSU2U Copyright (c) 2012		  **	
 **		Property of Midwestern State University  **
 **				Computer Science Dept. 			  **
 ** ************************************************/ 

// This class manages the campus map activity/screen

package com.android.msu2u;

import android.app.Activity;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class CampusMap extends Activity  {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campusmap);
    }
}