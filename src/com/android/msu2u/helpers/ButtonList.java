/***************************************************
 **				MSU2U Copyright (c) 2012		  **	
 **		Property of Midwerstern State University  **
 **				Computer Science Dept. 			  **
 ** ************************************************/

// This class will be used to create a custom ArrayAdapter 
// and to bind the objects(main menu items) with the ListView
// e.g "Campus Map" & "Directory" 

package com.android.msu2u.helpers;

public class ButtonList {
	public int icon;
	public String title;
	public String subtitle;
	public ButtonList(){
		super();
	}
	
	/**ButtonList Constructor 
	 * @param icon = path to icon in res folder
	 * @param title = ListItem/button title
	 * @param subtitle = ListItem/button subtitle
	 */
	public ButtonList(int icon, String title, String subtitle){
		super();
		this.icon = icon;
		this.title = title;
		this.subtitle = subtitle; 
	}
} // end ButtonList class