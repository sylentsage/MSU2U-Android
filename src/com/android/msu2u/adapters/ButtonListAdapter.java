/***************************************************
 **				MSU2U Copyright (c) 2012		  **	
 **		Property of Midwerstern State University  **
 **				Computer Science Dept. 			  **
 ** ************************************************/ 
// File: ButtonListAdapter.java
// This Class is a custom ArrayAdapter that will inherit the Android ArrayAdapter 
// class and will override the getView method. 

package com.android.msu2u.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.msu2u.R;
import com.android.msu2u.helpers.ButtonList;

public class ButtonListAdapter extends ArrayAdapter<ButtonList>{
	Context context;
	private ButtonList[] data = null;
	static int layoutResourceId;
	
	/**ButtonListAdapter Constructor
	 * @param context - use to pass the reference of the activity in which we will used this class
	 * @param layoutResourceId - resource id of the layout file we want to use for displaying each ListView item
	 * 							 The following layout will be list_row.xml
	 * @param data - An array of ButtonList class objects that will be used by the Adapter to display data. 
	 */
	public ButtonListAdapter(Context context, int layoutResourceId, ButtonList[] data){
		super(context, layoutResourceId, data);
        ButtonListAdapter.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data  = data; 
	}
	/**OVERRIDE: View getView()
	 * This method will be called for every item in the ListView to 
	 * create views with their properties set as we want. 
	 * The getView method is also using a temporary holder class 
	 * declared inside the ButtonListAdapter class.
	 */
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ButtonListHolder holder = null;
		
		if(row == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new ButtonListHolder();
			holder.imgIcon = (ImageView)row.findViewById(R.id.list_image);
			holder.txtTitle = (TextView)row.findViewById(R.id.title);
			holder.txtSubTitle = (TextView)row.findViewById(R.id.subtitle);
			
			row.setTag(holder);
		}else{
			holder = (ButtonListHolder)row.getTag();
		}
		
		ButtonList buttonList = data[position];
		holder.txtTitle.setText(buttonList.title);
		holder.txtSubTitle.setText(buttonList.subtitle);
		holder.imgIcon.setImageResource(buttonList.icon);
		
		return row;	
	}
	/**ButtonListHolder - Hold the basic elements of each button list item */
	static class ButtonListHolder{
		ImageView imgIcon;
		TextView txtTitle;
		TextView txtSubTitle;
		}
	 }// end ButtonListAdapter class