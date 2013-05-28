/***************************************************
 **				MSU2U Copyright (c) 2012		  **	
 **		Property of Midwerstern State University  **
 **				Computer Science Dept. 			  **
 ** ************************************************/ 

// This class manages the Main Menu activity/screen 

package com.android.msu2u;

import com.actionbarsherlock.app.SherlockActivity;
import com.android.msu2u.helpers.ButtonList;
import com.android.msu2u.adapters.ButtonListAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends SherlockActivity implements OnItemClickListener{ 
	
	// Variables 
	 private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		// Build 2D Array with Menu parameters for Xml file-was hope to add ListItems Dynamically
		// Currently Not Used
		String[] menu_items = getResources().getStringArray(R.array.Menu_Items);
		String[][] menu_Info = new String[menu_items.length][];
		String delimiter = ",";
		for (int count = 0; count < menu_items.length; count++ ){
			menu_Info[count] = menu_items[count].split(delimiter);
		}
		
		// Populate the array with items that will make uo the listview
		ButtonList buttonList_data[] = new ButtonList[]{
				new ButtonList(R.drawable.map,"Campus Map", "Find your way around campus"),
				new ButtonList(R.drawable.contact,"Directory", "Contact Faculty and Staff"),
				new ButtonList(R.drawable.calender,"Events", "View and Add MSU events to your calender"),
				new ButtonList(R.drawable.facebook,"Media", "News social media and images")	
			};
		ButtonListAdapter adapter = new ButtonListAdapter(this,R.layout.list_row, buttonList_data);
		listView = (ListView)findViewById(R.id.listView);
		listView.setClickable(true);
		listView.setOnItemClickListener(this);
		listView.setAdapter(adapter);
	}

	/**OVERRIDE: onItemClick
	 * Any item clicked/touched on the main menu list with open an new activity
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		RelativeLayout listItem = (RelativeLayout) v;
	    TextView clickedItemView = (TextView) listItem.findViewById(R.id.title);
	    String clickedItemString = clickedItemView.getText().toString();
	    Log.i("DisplayListCustom", "Click detected " + clickedItemString + ", position " + Integer.toString(position));
	    //Toast.makeText(getApplicationContext(),clickedItemString,Toast.LENGTH_SHORT).show();
	    
	    
	    try {
	    	// When Campus map is CLicked 
			if (clickedItemString.equals("Campus Map")){
				Intent myIntent = new Intent(MainMenu.this, CampusMap.class);
				myIntent.putExtra("button", clickedItemString);
				startActivity(myIntent);
				}
			// When Directory is Clicked
			else if (clickedItemString.equals("Directory")){
				Intent myIntent = new Intent(MainMenu.this, Directory.class);
				myIntent.putExtra("button", clickedItemString);
				startActivity(myIntent);
				}
			// When Events is Clicked
			else if (clickedItemString.equals("Events")){
				Intent myIntent = new Intent(MainMenu.this, Events.class);
				myIntent.putExtra("button", clickedItemString);
				startActivity(myIntent);
				}
			// When Media is Clicked
			else if (clickedItemString.equals("Media")){
				Intent myIntent = new Intent(MainMenu.this, Media.class);
				myIntent.putExtra("button", clickedItemString);
				startActivity(myIntent);
				}
		// if exception is catch: 1) Activity missing 2) New Activity not in Manifest
		} catch (Exception e) { 
			e.printStackTrace();
			 Toast.makeText(getApplicationContext(),"Activity Miss Match",Toast.LENGTH_SHORT).show();
		}
	} // end onItemClicked
} // end MainMenu Class
