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
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CampusMap extends Activity  {
	private final LatLng LOCATION_DILLARD = new LatLng(33.876892,-98.52134);
	private final LatLng LOCATION_MOFFETTLIBRARY = new LatLng(33.874683, -98.519240);
	private final LatLng LOCATION_BOLIN = new LatLng(33.873779,-98.519562);
	private final LatLng LOCATION_HARDIN = new LatLng(33.876171,-98.519796);
	private final LatLng LOCATION_BRIDWELL = new LatLng(33.877382,-98.52237);
	private final LatLng LOCATION_FAINFINE = new LatLng(33.873329,-98.522875);
	private final LatLng LOCATION_CLARKSC = new LatLng(33.874853,-98.521137);
	private final LatLng LOCATION_PIERCEHALL = new LatLng(33.874078,-98.522381);
	private final LatLng LOCATION_KILLINGSWORTH = new LatLng(33.87487,-98.522317);
	private final LatLng LOCATION_MCCULLOUGH = new LatLng(33.875084,-98.523111);
	private final LatLng LOCATION_MCCOY = new LatLng(33.876447,-98.522295);
	private final LatLng LOCATION_FERGUSON = new LatLng(33.875453,-98.521059);
	private final LatLng LOCATION_MARTIN = new LatLng(33.876148,-98.52166);
	private final LatLng LOCATION_PROTHRO = new LatLng(33.873837,-98.521104);
	private final LatLng LOCATION_BEAWOOD = new LatLng(33.873971,-98.521619);
	private final LatLng LOCATION_ODONOHOE = new LatLng(33.873962,-98.520579);
	
	private GoogleMap map;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campusmap);
        
        map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_CLARKSC, (float) 16.75);
		map.animateCamera(update);
		
		map.addMarker(new MarkerOptions().position(LOCATION_DILLARD).title("Dillard College of Business"));
		map.addMarker(new MarkerOptions().position(LOCATION_MOFFETTLIBRARY).title("Moffett Library"));
		map.addMarker(new MarkerOptions().position(LOCATION_BOLIN).title("Bolin Hall"));
		map.addMarker(new MarkerOptions().position(LOCATION_HARDIN).title("Hardin Administrative Building"));
		map.addMarker(new MarkerOptions().position(LOCATION_BRIDWELL).title("Bridwell Hall"));
		map.addMarker(new MarkerOptions().position(LOCATION_FAINFINE).title("Fain Fine Arts Building"));
		map.addMarker(new MarkerOptions().position(LOCATION_CLARKSC).title("Clark Student Center"));
		map.addMarker(new MarkerOptions().position(LOCATION_PIERCEHALL).title("Pierce Hall"));
		map.addMarker(new MarkerOptions().position(LOCATION_KILLINGSWORTH).title("Killingsworth Hall"));
		map.addMarker(new MarkerOptions().position(LOCATION_MCCULLOUGH).title("McCullough-Trigg"));
		map.addMarker(new MarkerOptions().position(LOCATION_MCCOY).title("McCoy Engineering Building"));
		map.addMarker(new MarkerOptions().position(LOCATION_FERGUSON).title("Ferguson Hall"));
		map.addMarker(new MarkerOptions().position(LOCATION_MARTIN).title("Martin Hall"));
		map.addMarker(new MarkerOptions().position(LOCATION_PROTHRO).title("Prothro-Yeager Hall"));
		map.addMarker(new MarkerOptions().position(LOCATION_BEAWOOD).title("Bea Wood Hall"));
		map.addMarker(new MarkerOptions().position(LOCATION_ODONOHOE).title("O'Donohoe Hall"));
    }
}