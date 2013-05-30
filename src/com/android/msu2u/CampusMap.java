/***************************************************
 **				MSU2U Copyright (c) 2012		  **	
 **		Property of Midwestern State University  **
 **				Computer Science Dept. 			  **
 ** ************************************************/

// This class manages the campus map activity/screen

package com.android.msu2u;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class CampusMap extends Activity implements OnInfoWindowClickListener {

	private final LatLng LOCATION_CLARKSC = new LatLng(33.874853, -98.521137);

	private GoogleMap map;
	private static final String LOG_TAG = "MSU2U";
	private int userIcon;
	private Polygon polygons[] = new Polygon[70];
	private boolean parking = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_campusmap);
		setUpMapIfNeeded();

		userIcon = R.drawable.chart;

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		map.getUiSettings().setZoomControlsEnabled(true);
		map.getUiSettings().setCompassEnabled(true);
		map.getUiSettings().setMyLocationButtonEnabled(true);
		map.getUiSettings().setAllGesturesEnabled(true);
		map.setOnInfoWindowClickListener(this);

		// Zoom to default location upon loading
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(
				LOCATION_CLARKSC, (float) 16.50);
		map.animateCamera(update);

		// Add Parking
		addStudentParking();
		addReservedParking();
		addResidentialParking();
		addHybridParking();
		parking = true;
	}

	/* TODO: When user click on marker's info box it will prompt 
	 * user to use navigation to navigate to location */
	 public void onInfoWindowClick(Marker marker) {
		Toast.makeText(getBaseContext(),
				"Info Window clicked@" + marker.getTitle(), Toast.LENGTH_SHORT)
				.show();
	}
	
	/*TODO: Use menu button to show user Map key and remove makers and parking
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_menu, menu);
		return true;
	}

	// handle choice from options menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// perform appropriate task based on
		switch (item.getItemId()) {
		case R.id.mapKey: // the user selected "Map Key"
			// Show custom toast with Key;
			Toast.makeText(getApplicationContext(), "Key Will Be Shown",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.parking: // the user selected "show/hide parking"
			if (parking == false) {
				addHybridParking();
				addReservedParking();
				addResidentialParking();
				addStudentParking();
				return true;
			} else {
				removeAllParking();
				parking = false;
				return true;
			}
		default:
			return super.onOptionsItemSelected(item);
		} // end switch
	} // end method onOptionsItemSelected
	
	*/

	// Adds Student Parking - Draws yellow Polygons on the Map
	private void addStudentParking() {
		// Instantiates a new Polygon object and adds points to define a
		// Moffett Library Parking Lot
		PolygonOptions rectOptions = new PolygonOptions()
				.add(new LatLng(33.874715, -98.520163),
						new LatLng(33.874727, -98.519683),
						new LatLng(33.874425, -98.519648),
						new LatLng(33.874413, -98.519364),
						new LatLng(33.874353, -98.519353),
						new LatLng(33.874346, -98.520168),
						new LatLng(33.874715, -98.520163))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		Polygon polygon0 = map.addPolygon(rectOptions);
		polygons[0] = polygon0;

		// Bolin Parking Lot
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.873431, -98.519726),
						new LatLng(33.873428, -98.519053),
						new LatLng(33.872921, -98.519058),
						new LatLng(33.872943, -98.520176),
						new LatLng(33.873331, -98.520174),
						new LatLng(33.873346, -98.519742),
						new LatLng(33.873431, -98.519726))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		Polygon polygon1 = map.addPolygon(rectOptions);
		polygons[1] = polygon1;

		// Prothro Yeager Parking Lot
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.873493, -98.521893),
						new LatLng(33.873456, -98.520858),
						new LatLng(33.872936, -98.520847),
						new LatLng(33.872926, -98.521933),
						new LatLng(33.873493, -98.521893))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[2] = map.addPolygon(rectOptions);

		// Louis Rodriguez New
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.876994, -98.523559),
						new LatLng(33.87701, -98.52288),
						new LatLng(33.876544, -98.522876),
						new LatLng(33.876536, -98.52356),
						new LatLng(33.876994, -98.523559))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[3] = map.addPolygon(rectOptions);

		// Louis Rodriguez New 2
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.877939, -98.524552),
						new LatLng(33.877931, -98.523754),
						new LatLng(33.877786, -98.523744),
						new LatLng(33.877786, -98.524169),
						new LatLng(33.877396, -98.524173),
						new LatLng(33.877394, -98.524583),
						new LatLng(33.877939, -98.524552))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[4] = map.addPolygon(rectOptions);

		// Dillard Commuter 1
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.877616, -98.521645),
						new LatLng(33.877627, -98.520999),
						new LatLng(33.877499, -98.520994),
						new LatLng(33.87749, -98.521011),
						new LatLng(33.877389, -98.521013),
						new LatLng(33.877363, -98.521654),
						new LatLng(33.877616, -98.521645))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[5] = map.addPolygon(rectOptions);

		// Dillard Commuter 2 East
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.87761, -98.520882),
						new LatLng(33.877611, -98.520446),
						new LatLng(33.877559, -98.520446),
						new LatLng(33.877499, -98.520429),
						new LatLng(33.877381, -98.520431),
						new LatLng(33.877384, -98.520876),
						new LatLng(33.877493, -98.520875),
						new LatLng(33.877516, -98.520887),
						new LatLng(33.87761, -98.520882)).fillColor(0x7ff0ff00)
				.strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[6] = map.addPolygon(rectOptions);

		// Hardin North
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.877549, -98.520177),
						new LatLng(33.877544, -98.520097),
						new LatLng(33.877599, -98.519987),
						new LatLng(33.877646, -98.519986),
						new LatLng(33.877649, -98.519436),
						new LatLng(33.877568, -98.519382),
						new LatLng(33.877044, -98.519395),
						new LatLng(33.876939, -98.519414),
						new LatLng(33.876948, -98.519952),
						new LatLng(33.877076, -98.519984),
						new LatLng(33.8771, -98.52018),
						new LatLng(33.877549, -98.520177))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[7] = map.addPolygon(rectOptions);

		// Hardin Street Moffett Commuter
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.875333, -98.519335),
						new LatLng(33.875335, -98.518767),
						new LatLng(33.87531, -98.518766),
						new LatLng(33.87531, -98.519336),
						new LatLng(33.875333, -98.519335))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[8] = map.addPolygon(rectOptions);

		// Hardin Taft South Commuter
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.876175, -98.518597),
						new LatLng(33.876204, -98.518562),
						new LatLng(33.875407, -98.518559),
						new LatLng(33.87539, -98.518603),
						new LatLng(33.876175, -98.518597))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[9] = map.addPolygon(rectOptions);

		// Hardin Taft North Commuter
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.877515, -98.518589),
						new LatLng(33.87755, -98.518552),
						new LatLng(33.876361, -98.51856),
						new LatLng(33.876333, -98.518597),
						new LatLng(33.877515, -98.518589))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[10] = map.addPolygon(rectOptions);

		// Student Union Street East
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.875166, -98.52031),
						new LatLng(33.875165, -98.520284),
						new LatLng(33.87475, -98.520286),
						new LatLng(33.874751, -98.520312),
						new LatLng(33.875166, -98.52031)).fillColor(0x7ff0ff00)
				.strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[11] = map.addPolygon(rectOptions);

		// Student Union Street North
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.875273, -98.521872),
						new LatLng(33.875273, -98.520392),
						new LatLng(33.875245, -98.520391),
						new LatLng(33.875241, -98.521868),
						new LatLng(33.875273, -98.521872))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[12] = map.addPolygon(rectOptions);

		// Fain
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.875358, -98.521847),
						new LatLng(33.875429, -98.521761),
						new LatLng(33.875429, -98.521471),
						new LatLng(33.875388, -98.521421),
						new LatLng(33.875388, -98.521721),
						new LatLng(33.875355, -98.52176),
						new LatLng(33.875358, -98.521847))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[13] = map.addPolygon(rectOptions);

		// Fain West Commuter
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.875683, -98.5219),
						new LatLng(33.875721, -98.521839),
						new LatLng(33.875458, -98.521845),
						new LatLng(33.875412, -98.521896),
						new LatLng(33.875683, -98.5219)).fillColor(0x7ff0ff00)
				.strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[14] = map.addPolygon(rectOptions);

		// Power Plant South
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.872957, -98.52062),
						new LatLng(33.87295, -98.520409),
						new LatLng(33.872896, -98.520408),
						new LatLng(33.872898, -98.520619),
						new LatLng(33.872957, -98.52062)).fillColor(0x7ff0ff00)
				.strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[15] = map.addPolygon(rectOptions);

		// DL Ligon Street West
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.872773, -98.520433),
						new LatLng(33.872772, -98.520388),
						new LatLng(33.870832, -98.520386),
						new LatLng(33.870804, -98.520442),
						new LatLng(33.872773, -98.520433))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[16] = map.addPolygon(rectOptions);

		// DL Ligon Street South East
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.871046, -98.520277),
						new LatLng(33.871078, -98.520227),
						new LatLng(33.870782, -98.520226),
						new LatLng(33.87075, -98.520282),
						new LatLng(33.871046, -98.520277))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[17] = map.addPolygon(rectOptions);

		// DL Ligon Street North West
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.872826, -98.520118),
						new LatLng(33.872834, -98.519123),
						new LatLng(33.87281, -98.519122),
						new LatLng(33.872802, -98.520115),
						new LatLng(33.872826, -98.520118))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[18] = map.addPolygon(rectOptions);

		// DL Ligon Street North East
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.872836, -98.519022),
						new LatLng(33.87282, -98.518684),
						new LatLng(33.872789, -98.518648),
						new LatLng(33.872807, -98.519021),
						new LatLng(33.872836, -98.519022))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[19] = map.addPolygon(rectOptions);

		// Soccer Softball
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.872426, -98.523716),
						new LatLng(33.872425, -98.523502),
						new LatLng(33.871621, -98.523508),
						new LatLng(33.871614, -98.523717),
						new LatLng(33.872426, -98.523716))
				.fillColor(0x7ff0ff00).strokeColor(0x7ff0ff00);

		// Get back the mutable Polygon
		polygons[20] = map.addPolygon(rectOptions);
	}

	// Adds Reserved Parking - Draws Blue Polygons on the Map
	private void addReservedParking() {
		// Moffett Library Reserved
		PolygonOptions rectOptions = new PolygonOptions()
				.add(new LatLng(33.875228, -98.520171),
						new LatLng(33.875241, -98.519275),
						new LatLng(33.875162, -98.519259),
						new LatLng(33.875143, -98.519629),
						new LatLng(33.874872, -98.519632),
						new LatLng(33.874894, -98.520158),
						new LatLng(33.875228, -98.520171))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[21] = map.addPolygon(rectOptions);

		// Bolin Hall Reserved 1
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.8742, -98.520176),
						new LatLng(33.874197, -98.519981),
						new LatLng(33.873617, -98.519989),
						new LatLng(33.873633, -98.520182),
						new LatLng(33.8742, -98.520176)).fillColor(0x7f269cb5)
				.strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[22] = map.addPolygon(rectOptions);

		// Bolin Hall Reserved 2
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.873457, -98.520176),
						new LatLng(33.87345, -98.519728),
						new LatLng(33.873381, -98.519718),
						new LatLng(33.873353, -98.520168),
						new LatLng(33.873457, -98.520176))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[23] = map.addPolygon(rectOptions);

		// Prothro Yeager Parking Lot 1
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.873696, -98.521893),
						new LatLng(33.873703, -98.521257),
						new LatLng(33.873614, -98.521204),
						new LatLng(33.873542, -98.521204),
						new LatLng(33.873542, -98.521904),
						new LatLng(33.873696, -98.521893))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[24] = map.addPolygon(rectOptions);

		// Prothro Yeager Parking Lot 2
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.873692, -98.521045),
						new LatLng(33.873701, -98.520391),
						new LatLng(33.873556, -98.520388),
						new LatLng(33.873547, -98.521072),
						new LatLng(33.873635, -98.521083),
						new LatLng(33.873692, -98.521045))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[25] = map.addPolygon(rectOptions);

		// Prothro Yeager Parking Lot 3
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.873506, -98.520831),
						new LatLng(33.8735, -98.520737),
						new LatLng(33.87293, -98.520753),
						new LatLng(33.872939, -98.52086),
						new LatLng(33.873506, -98.520831))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[26] = map.addPolygon(rectOptions);

		// Prothro Yeager Parking Lot 4
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.874218, -98.521937),
						new LatLng(33.874224, -98.521876),
						new LatLng(33.873723, -98.521874),
						new LatLng(33.873729, -98.521937),
						new LatLng(33.874218, -98.521937))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[27] = map.addPolygon(rectOptions);

		// Teepee Parking Lot 4
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.873384, -98.522066),
						new LatLng(33.873402, -98.522015),
						new LatLng(33.873026, -98.522016),
						new LatLng(33.873026, -98.52207),
						new LatLng(33.873384, -98.522066))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[28] = map.addPolygon(rectOptions);

		// Teepee Parking Lot 4
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.873611, -98.522218),
						new LatLng(33.873604, -98.522133),
						new LatLng(33.873541, -98.522134),
						new LatLng(33.873543, -98.522218),
						new LatLng(33.873611, -98.522218))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[29] = map.addPolygon(rectOptions);

		// Fain Parking Lot 1
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.872905, -98.5234),
						new LatLng(33.872896, -98.522312),
						new LatLng(33.872877, -98.522311),
						new LatLng(33.872874, -98.523392),
						new LatLng(33.872905, -98.5234)).fillColor(0x7f269cb5)
				.strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[30] = map.addPolygon(rectOptions);

		// McCullough Parking Lot
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.875254, -98.522779),
						new LatLng(33.875254, -98.522711),
						new LatLng(33.875063, -98.52271),
						new LatLng(33.875065, -98.522784),
						new LatLng(33.875254, -98.522779))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[31] = map.addPolygon(rectOptions);

		// Paint Building Lot
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.876533, -98.523556),
						new LatLng(33.876532, -98.522873),
						new LatLng(33.876406, -98.522873),
						new LatLng(33.876388, -98.523556),
						new LatLng(33.876533, -98.523556))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[32] = map.addPolygon(rectOptions);

		// Police Lot(Reserved)
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.877546, -98.524029),
						new LatLng(33.877542, -98.523781),
						new LatLng(33.877386, -98.523781),
						new LatLng(33.877384, -98.52405),
						new LatLng(33.877428, -98.524047),
						new LatLng(33.877433, -98.524077),
						new LatLng(33.877499, -98.524078),
						new LatLng(33.877498, -98.524025),
						new LatLng(33.877546, -98.524029))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[33] = map.addPolygon(rectOptions);

		// Bridwell 0
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.87785, -98.522928),
						new LatLng(33.877851, -98.522872),
						new LatLng(33.877703, -98.522873),
						new LatLng(33.877705, -98.522932),
						new LatLng(33.87785, -98.522928)).fillColor(0x7f269cb5)
				.strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[34] = map.addPolygon(rectOptions);

		// Bridwell 1
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.877651, -98.52293),
						new LatLng(33.877654, -98.522877),
						new LatLng(33.877475, -98.52288),
						new LatLng(33.877484, -98.522934),
						new LatLng(33.877651, -98.52293)).fillColor(0x7f269cb5)
				.strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[35] = map.addPolygon(rectOptions);

		// Bridwell 2
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.877848, -98.522794),
						new LatLng(33.877859, -98.522735),
						new LatLng(33.877541, -98.522714),
						new LatLng(33.877531, -98.522788),
						new LatLng(33.877848, -98.522794))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[36] = map.addPolygon(rectOptions);

		// Bridwell 3
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.877373, -98.522798),
						new LatLng(33.877373, -98.522737),
						new LatLng(33.877139, -98.522742),
						new LatLng(33.877134, -98.522793),
						new LatLng(33.877373, -98.522798))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[37] = map.addPolygon(rectOptions);

		// Dillard
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.877612, -98.521795),
						new LatLng(33.877616, -98.521645),
						new LatLng(33.877363, -98.521654),
						new LatLng(33.877356, -98.521066),
						new LatLng(33.877267, -98.521072),
						new LatLng(33.877276, -98.521217),
						new LatLng(33.877204, -98.52122),
						new LatLng(33.877207, -98.521499),
						new LatLng(33.877213, -98.52181),
						new LatLng(33.877612, -98.521795))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[36] = map.addPolygon(rectOptions);

		// Dillard 2 Coords (West South)
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.877158, -98.521792),
						new LatLng(33.87716, -98.521504),
						new LatLng(33.877107, -98.521504),
						new LatLng(33.877099, -98.52179),
						new LatLng(33.877158, -98.521792))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[39] = map.addPolygon(rectOptions);

		// Dillard 3 Coords (East South)
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.877331, -98.520815),
						new LatLng(33.877329, -98.520433),
						new LatLng(33.877151, -98.520432),
						new LatLng(33.877154, -98.520354),
						new LatLng(33.877092, -98.520354),
						new LatLng(33.877095, -98.520582),
						new LatLng(33.877201, -98.520585),
						new LatLng(33.877204, -98.520669),
						new LatLng(33.877273, -98.520671),
						new LatLng(33.877278, -98.520815),
						new LatLng(33.877331, -98.520815))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[40] = map.addPolygon(rectOptions);

		// Hardin Administration Building
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.876984, -98.520181),
						new LatLng(33.876985, -98.52001),
						new LatLng(33.876309, -98.520013),
						new LatLng(33.876281, -98.520064),
						new LatLng(33.87622, -98.520068),
						new LatLng(33.876251, -98.520012),
						new LatLng(33.876034, -98.520013),
						new LatLng(33.875917, -98.52009),
						new LatLng(33.875916, -98.520144),
						new LatLng(33.876051, -98.520143),
						new LatLng(33.876098, -98.520189),
						new LatLng(33.876984, -98.520181))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[41] = map.addPolygon(rectOptions);

		// Hardin Street Plaza 1 North
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.876894, -98.520301),
						new LatLng(33.876895, -98.520272),
						new LatLng(33.876251, -98.520274),
						new LatLng(33.87625, -98.520304),
						new LatLng(33.876894, -98.520301))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[42] = map.addPolygon(rectOptions);

		// Hardin Street Plaza 2 Middle
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.876115, -98.520304),
						new LatLng(33.876115, -98.520277),
						new LatLng(33.875663, -98.52028),
						new LatLng(33.875662, -98.520306),
						new LatLng(33.876115, -98.520304))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[43] = map.addPolygon(rectOptions);

		// Hardin Street Plaza 3 South
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.87559, -98.520307),
						new LatLng(33.875588, -98.520281),
						new LatLng(33.875473, -98.520282),
						new LatLng(33.875474, -98.52031),
						new LatLng(33.87559, -98.520307)).fillColor(0x7f269cb5)
				.strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[44] = map.addPolygon(rectOptions);

		// Hardin Street Moffett West
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.875338, -98.520103),
						new LatLng(33.875335, -98.519838),
						new LatLng(33.875312, -98.519838),
						new LatLng(33.875312, -98.520103),
						new LatLng(33.875338, -98.520103))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[45] = map.addPolygon(rectOptions);

		// Hardin Street Moffett East
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.875337, -98.519752),
						new LatLng(33.875335, -98.519399),
						new LatLng(33.875311, -98.519399),
						new LatLng(33.875311, -98.519749),
						new LatLng(33.875337, -98.519752))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[46] = map.addPolygon(rectOptions);

		// Bolin Street Reserved
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.874181, -98.520318),
						new LatLng(33.874179, -98.520288),
						new LatLng(33.873757, -98.520286),
						new LatLng(33.873757, -98.520317),
						new LatLng(33.874181, -98.520318))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[47] = map.addPolygon(rectOptions);

		// Fain West Reserved
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.875974, -98.521891),
						new LatLng(33.875988, -98.521872),
						new LatLng(33.875956, -98.521838),
						new LatLng(33.875733, -98.52184),
						new LatLng(33.875681, -98.521924),
						new LatLng(33.875411, -98.521935),
						new LatLng(33.875469, -98.522014),
						new LatLng(33.875556, -98.522014),
						new LatLng(33.87561, -98.5221),
						new LatLng(33.876033, -98.522098),
						new LatLng(33.876046, -98.522042),
						new LatLng(33.876088, -98.522079),
						new LatLng(33.876103, -98.522055),
						new LatLng(33.876103, -98.521939),
						new LatLng(33.876056, -98.52189),
						new LatLng(33.875974, -98.521891))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[48] = map.addPolygon(rectOptions);

		// Daniel Building South
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.875494, -98.522611),
						new LatLng(33.875547, -98.522555),
						new LatLng(33.875548, -98.52224),
						new LatLng(33.875527, -98.522217),
						new LatLng(33.875503, -98.52224),
						new LatLng(33.875435, -98.522236),
						new LatLng(33.875412, -98.522209),
						new LatLng(33.875393, -98.522232),
						new LatLng(33.875394, -98.52254),
						new LatLng(33.875446, -98.522596),
						new LatLng(33.875494, -98.522611))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[49] = map.addPolygon(rectOptions);

		// DL Ligon Street East Middle
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.87176, -98.520273),
						new LatLng(33.871783, -98.520223),
						new LatLng(33.871081, -98.520226),
						new LatLng(33.871047, -98.520278),
						new LatLng(33.871753, -98.520274))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[50] = map.addPolygon(rectOptions);

		// DL Ligon Street East Middle
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.872549, -98.52027),
						new LatLng(33.872552, -98.520219),
						new LatLng(33.87194, -98.520221),
						new LatLng(33.871908, -98.520274),
						new LatLng(33.872549, -98.52027)).fillColor(0x7f269cb5)
				.strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[51] = map.addPolygon(rectOptions);

		// DL Ligon Street North
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.872834, -98.519123),
						new LatLng(33.872836, -98.519022),
						new LatLng(33.872807, -98.519021),
						new LatLng(33.87281, -98.519122),
						new LatLng(33.872834, -98.519123))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[52] = map.addPolygon(rectOptions);

		// Soccer Softball North
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.872615, -98.523702),
						new LatLng(33.872654, -98.523493),
						new LatLng(33.872425, -98.523502),
						new LatLng(33.872426, -98.523716),
						new LatLng(33.872615, -98.523702))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[53] = map.addPolygon(rectOptions);

		// Wellness Center North
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.870006, -98.523093),
						new LatLng(33.870005, -98.523033),
						new LatLng(33.869877, -98.523034),
						new LatLng(33.869881, -98.52309),
						new LatLng(33.870006, -98.523093))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[54] = map.addPolygon(rectOptions);

		// Wellness Center South
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.86982, -98.523091),
						new LatLng(33.869819, -98.523034),
						new LatLng(33.869538, -98.523029),
						new LatLng(33.869544, -98.523087),
						new LatLng(33.86982, -98.523091)).fillColor(0x7f269cb5)
				.strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[55] = map.addPolygon(rectOptions);

		// Sundancer North
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.871216, -98.525003),
						new LatLng(33.871219, -98.52495),
						new LatLng(33.87114, -98.524948),
						new LatLng(33.871142, -98.52501),
						new LatLng(33.871216, -98.525003))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[56] = map.addPolygon(rectOptions);

		// Sundancer South
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.870874, -98.525012),
						new LatLng(33.870872, -98.524955),
						new LatLng(33.870798, -98.524951),
						new LatLng(33.870797, -98.525013),
						new LatLng(33.870874, -98.525012))
				.fillColor(0x7f269cb5).strokeColor(0x7f269cb5);

		// Get back the mutable Polygon
		polygons[57] = map.addPolygon(rectOptions);
	}

	// Adds Residential Parking - Draws Red Polygons on the Map
	private void addResidentialParking() {
		// Teepee Parking Lot (Residential)
		PolygonOptions rectOptions = new PolygonOptions()
				.add(new LatLng(33.874362, -98.522066),
						new LatLng(33.874366, -98.521987),
						new LatLng(33.87354, -98.521999),
						new LatLng(33.873541, -98.522065),
						new LatLng(33.874362, -98.522066))
				.fillColor(0x7ff90404).strokeColor(0x7ff90404);
		// Get back the mutable Polygon
		polygons[58] = map.addPolygon(rectOptions);

		// Fain Residential Lot (Residential)
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.875044, -98.523568),
						new LatLng(33.875049, -98.52348),
						new LatLng(33.874992, -98.523457),
						new LatLng(33.874994, -98.522735),
						new LatLng(33.874761, -98.522743),
						new LatLng(33.874687, -98.522833),
						new LatLng(33.87456, -98.522816),
						new LatLng(33.874549, -98.523493),
						new LatLng(33.874683, -98.523529),
						new LatLng(33.874699, -98.523585),
						new LatLng(33.875044, -98.523568))
				.fillColor(0x7ff90404).strokeColor(0x7ff90404);
		// Get back the mutable Polygon
		polygons[59] = map.addPolygon(rectOptions);

		// Fain Residential Lot (Residential)
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.874464, -98.523501),
						new LatLng(33.874472, -98.522868),
						new LatLng(33.874313, -98.522852),
						new LatLng(33.874297, -98.522755),
						new LatLng(33.874067, -98.522745),
						new LatLng(33.874062, -98.522822),
						new LatLng(33.874007, -98.522825),
						new LatLng(33.874013, -98.5235),
						new LatLng(33.874464, -98.523501))
				.fillColor(0x7ff90404).strokeColor(0x7ff90404);
		// Get back the mutable Polygon
		polygons[60] = map.addPolygon(rectOptions);

		// Fain Residential Lot (Residential)
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.875348, -98.524573),
						new LatLng(33.87536, -98.523785),
						new LatLng(33.875204, -98.523784),
						new LatLng(33.875207, -98.524244),
						new LatLng(33.875003, -98.524237),
						new LatLng(33.875012, -98.524438),
						new LatLng(33.873937, -98.524446),
						new LatLng(33.873926, -98.524256),
						new LatLng(33.873723, -98.524247),
						new LatLng(33.87372, -98.523828),
						new LatLng(33.873547, -98.52382),
						new LatLng(33.87355, -98.524523),
						new LatLng(33.873614, -98.524526),
						new LatLng(33.87362, -98.524641),
						new LatLng(33.875251, -98.524636),
						new LatLng(33.875256, -98.524571),
						new LatLng(33.875348, -98.524573))
				.fillColor(0x7ff90404).strokeColor(0x7ff90404);
		// Get back the mutable Polygon
		polygons[61] = map.addPolygon(rectOptions);

		// Bridwell Courts (Residential)
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.877349, -98.524567),
						new LatLng(33.877343, -98.523808),
						new LatLng(33.877299, -98.523805),
						new LatLng(33.877307, -98.524365),
						new LatLng(33.877217, -98.524365),
						new LatLng(33.877215, -98.524434),
						new LatLng(33.877084, -98.524436),
						new LatLng(33.87708, -98.524366),
						new LatLng(33.876974, -98.524362),
						new LatLng(33.876979, -98.523815),
						new LatLng(33.876892, -98.523809),
						new LatLng(33.876907, -98.524547),
						new LatLng(33.877349, -98.524567))
				.fillColor(0x7ff90404).strokeColor(0x7ff90404);
		// Get back the mutable Polygon
		polygons[62] = map.addPolygon(rectOptions);

		// Sunwatcher Street North
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.874694, -98.523767),
						new LatLng(33.874716, -98.523739),
						new LatLng(33.874513, -98.523738),
						new LatLng(33.874516, -98.523769),
						new LatLng(33.874694, -98.523767))
				.fillColor(0x7ff90404).strokeColor(0x7ff90404);
		// Get back the mutable Polygon
		polygons[63] = map.addPolygon(rectOptions);

		// Sunwatcher Street South
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.874418, -98.523769),
						new LatLng(33.874418, -98.523741),
						new LatLng(33.874186, -98.52374),
						new LatLng(33.874184, -98.523771),
						new LatLng(33.874418, -98.523769))
				.fillColor(0x7ff90404).strokeColor(0x7ff90404);
		// Get back the mutable Polygon
		polygons[64] = map.addPolygon(rectOptions);

		// Sundance Court
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.871006, -98.526123),
						new LatLng(33.871196, -98.525611),
						new LatLng(33.871155, -98.525586),
						new LatLng(33.871172, -98.525552),
						new LatLng(33.871212, -98.525584),
						new LatLng(33.871308, -98.525424),
						new LatLng(33.87127, -98.525388),
						new LatLng(33.871289, -98.525357),
						new LatLng(33.871334, -98.525358),
						new LatLng(33.871335, -98.525158),
						new LatLng(33.871286, -98.525155),
						new LatLng(33.871286, -98.525088),
						new LatLng(33.871388, -98.525088),
						new LatLng(33.871438, -98.525145),
						new LatLng(33.871482, -98.525082),
						new LatLng(33.871544, -98.525033),
						new LatLng(33.871712, -98.524758),
						new LatLng(33.871724, -98.524657),
						new LatLng(33.871776, -98.524653),
						new LatLng(33.871771, -98.523972),
						new LatLng(33.871608, -98.52397),
						new LatLng(33.87166, -98.524709),
						new LatLng(33.871483, -98.525006),
						new LatLng(33.871434, -98.525007),
						new LatLng(33.87144, -98.524929),
						new LatLng(33.871219, -98.52495),// 1
						new LatLng(33.871216, -98.525003),//
						new LatLng(33.871142, -98.52501),//
						new LatLng(33.87114, -98.524948),//
						new LatLng(33.870872, -98.524955),// 2
						new LatLng(33.870874, -98.525012),//
						new LatLng(33.870797, -98.525013),//
						new LatLng(33.870798, -98.524951),//
						new LatLng(33.870752, -98.525012),
						new LatLng(33.870724, -98.525014),
						new LatLng(33.870729, -98.525119),
						new LatLng(33.870683, -98.52512),
						new LatLng(33.870682, -98.526101),
						new LatLng(33.870741, -98.526101),
						new LatLng(33.870742, -98.526167),
						new LatLng(33.870939, -98.526166),
						new LatLng(33.87094, -98.526105),
						new LatLng(33.870961, -98.526098),
						new LatLng(33.871006, -98.526123))
				.fillColor(0x7ff90404).strokeColor(0x7ff90404);
		// Get back the mutable Polygon
		polygons[65] = map.addPolygon(rectOptions);

	}

	// Adds Hybrid Parking - Draws Orange Polygons on the Map
	private void addHybridParking() {
		// Bolin Parking Lot 2 (Reserved)
		PolygonOptions rectOptions = new PolygonOptions()
				.add(new LatLng(33.872801, -98.522075),
						new LatLng(33.872769, -98.520471),
						new LatLng(33.872451, -98.520471),
						new LatLng(33.872385, -98.520547),
						new LatLng(33.872399, -98.522075))
				.fillColor(0x7ff9b104).strokeColor(0x7ff9b104);
		// Get back the mutable Polygon
		polygons[66] = map.addPolygon(rectOptions);

		// Church1 Louis Rodriguez Drive
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.873521, -98.52512),
						new LatLng(33.873529, -98.524349),
						new LatLng(33.873479, -98.52435),
						new LatLng(33.873463, -98.524715),
						new LatLng(33.873369, -98.524716),
						new LatLng(33.873372, -98.525124),
						new LatLng(33.873521, -98.52512)).fillColor(0x7ff9b104)
				.strokeColor(0x7ff9b104);
		// Get back the mutable Polygon
		polygons[67] = map.addPolygon(rectOptions);

		// Louis Rodriguez Drive Hybrid
		rectOptions = new PolygonOptions()
				.add(new LatLng(33.876498, -98.524306),
						new LatLng(33.876503, -98.523775),
						new LatLng(33.875786, -98.523765),
						new LatLng(33.875796, -98.524315),
						new LatLng(33.876498, -98.524306))
				.fillColor(0x7ff9b104).strokeColor(0x7ff9b104);
		// Get back the mutable Polygon
		polygons[68] = map.addPolygon(rectOptions);

	}

	// Remove all parking - Delete Polygons from map
	private void removeAllParking() {
		for (Polygon poly : polygons) {
			poly.remove();
		}
	}

	protected void retrieveAndAddCities() throws IOException {
		// HttpURLConnection conn = null;
		final StringBuilder json = new StringBuilder();
		InputStream file = getResources().openRawResource(R.raw.buildings);
		char[] buffer = new char[1024];
		try {

			Reader reader = new BufferedReader(new InputStreamReader(file,
					"UTF-8"));
			int n;
			while ((n = reader.read(buffer)) != -1) {
				// writer.write(buffer, 0, n);
				json.append(buffer, 0, n);
			}
		} catch (IOException e) {
			Log.e(LOG_TAG, "Error connecting to service", e);
			throw new IOException("Error connecting to service", e);
		} finally {
			file.close();
		}

		// Create markers for the city data.
		// Must run this on the UI thread since it's a UI operation.
		runOnUiThread(new Runnable() {
			public void run() {
				try {
					createMarkersFromJson(json.toString());
				} catch (JSONException e) {
					Log.e(LOG_TAG, "Error processing JSON", e);
				}
			}
		});
	}

	void createMarkersFromJson(String json) throws JSONException {
		// De-serialize the JSON string into an array of city objects
		JSONArray jsonArray = new JSONArray(json);
		for (int i = 0; i < jsonArray.length(); i++) {
			// Create a marker for each city in the JSON data.
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			map.addMarker(new MarkerOptions()
					.title(jsonObj.getString("name"))
					// .snippet(jsonObj.getString("info"))
					.position(
							new LatLng(jsonObj.getDouble("latitude"), jsonObj
									.getDouble("longitude")))
					.icon(BitmapDescriptorFactory.fromResource(userIcon)));
		}
	}

	private void setUpMap() {
		// Retrieve the city data from the web service
		// In a worker thread since it's a network operation.
		new Thread(new Runnable() {
			public void run() {
				try {
					retrieveAndAddCities();
				} catch (IOException e) {
					Log.e(LOG_TAG, "Cannot retrive cities", e);
					return;
				}
			}
		}).start();
	}

	private void setUpMapIfNeeded() {
		if (map == null) {
			map = ((MapFragment) getFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			if (map != null) {
				setUpMap();
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

}