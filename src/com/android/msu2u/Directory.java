/***************************************************
 **				MSU2U Copyright (c) 2012		  **	
 **		Property of Midwerstern State University  **
 **				Computer Science Dept. 			  **
 ** ************************************************/ 

// This class manages the directory activity/screen 

package com.android.msu2u;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays; 


import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import android.content.Intent;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import com.actionbarsherlock.app.SherlockActivity;
import com.android.msu2u.adapters.ButtonListAdapter;
import com.android.msu2u.adapters.Contact;
import com.android.msu2u.helpers.ButtonList;
//import com.deitel.addressbook.AddressBook;
//import com.deitel.addressbook.ViewContact;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Directory extends SherlockActivity implements OnItemClickListener{
	
	public ListView listview;
	public Contact[] people;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_directory);
        
    	//Call to the parsing task
        SyncParser parse;
        parse = new SyncParser();
        parse.execute();
        
        Intent i = getIntent();

	} // end OnCreate
	
	//Asynctask that will prevent us doing Parsing in the main thread 
	private class SyncParser extends AsyncTask<Void, Void, Void>
	{
		private ProgressDialog Dialog;
        Contact[] contacts;
        /*
         * This Displays dialog for loading
         * @see android.os.AsyncTask#onPreExecute()
         */
		@Override
		protected void onPreExecute(){
			super.onPreExecute();
	        Dialog = new ProgressDialog(Directory.this);
	        Dialog.setMessage("Loading....");
	        Dialog.show();
		}
		
		/*
		 * This sections read in data from our web
		 * source and then 
		 * using GSON creates an array of objects of Type Contact
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected Void doInBackground(Void... params) {	
			String url = "http://cs2.mwsu.edu/~msu2u/get_contacts_from_db.php";
    		HttpGet getRequest = new HttpGet(url);
    		
    		//WEb request
    		try 
    		{
    			DefaultHttpClient httpClient = new DefaultHttpClient();
    			HttpResponse getResponse = httpClient.execute(getRequest);
    			final int statusCode = getResponse.getStatusLine().getStatusCode();
    			//Error code output from failure to get response
    			if (statusCode != HttpStatus.SC_OK) { 
    	            Log.w(getClass().getSimpleName(), "Error " + statusCode + " for URL " + url); 
    	            return null;
    	        }

    			HttpEntity getResponseEntity = getResponse.getEntity();
    			InputStream httpResponseStream = getResponseEntity.getContent();
    			Reader inputStreamReader = new InputStreamReader(httpResponseStream);
    			
    			//Contacts array is loaded with data from the gson
                Gson gson = new Gson();
                this.contacts = gson.fromJson(inputStreamReader, Contact[].class);
                Contact temp[] = new Contact[this.contacts.length-1];
                
                //Removing a null value at the end of our array
                for (int i=0; i<this.contacts.length-1;i++)
                {
                	if (this.contacts[i]!= null)
                		temp[i]=this.contacts[i];
                }
                this.contacts = temp;
                Arrays.sort(this.contacts);
                people=this.contacts;
			}
			catch (IOException e)
			{
				getRequest.abort();
    	        Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
			}
			
			return null;
		}
		

		protected void onPostExecute(Void result){
			super.onPostExecute(result);
			ButtonList[] data = new ButtonList[contacts.length-1]; //There is a null value at the end of the json so we have a null in our array this removes it
			
			
			/*
			 * Loop for dynamically creating a list of our buttons based 
			 * on the data from contacts 
			 */
			for (int i=0; i<contacts.length-1;i++)
			{
				if (contacts[i]!= null)
				{
					String Title = contacts[i].getName_Prefix() + contacts[i].getFName() + " " + contacts[i].getLName();
					String Subtitle = contacts[i].getPosition_2();
					ButtonList item = new ButtonList(R.drawable.midwesternstatelogo,Title,Subtitle);
					data[i]=item;
				}
			}
			
			ButtonListAdapter adapter = new ButtonListAdapter(Directory.this,R.layout.list_row, data);
			listview = (ListView)findViewById(R.id.listView1);
			listview.setClickable(true);
			listview.setOnItemClickListener(Directory.this);
			listview.setAdapter(adapter);
			
			Dialog.dismiss();
		}
	}//End of Asynctask

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		RelativeLayout listItem = (RelativeLayout) v;
		TextView clickedItemView = (TextView) listItem.findViewById(R.id.title);
		
		//For debugging purpposes
		String clickedItemString = clickedItemView.getText().toString();
		Log.i("DisplayListCustom", "Click detected " + clickedItemString + ", position " + Integer.toString(position) + " and the Data is " + people[position].toString());
		//Setting th
		String fname = people[position].getFName()+ " " + people[position].getLName();
		String department = people[position].getDept_1();
		String email = people[position].getEmail();
		String phone1 = people[position].getPhone1();
		String fax = people[position].getFax1();
		String image = people[position].getPicture();
		
		//Putting the values into our intent
		Intent myIntent = new Intent(Directory.this,ViewContact.class);
		myIntent.putExtra("fname",fname);
		myIntent.putExtra("department",department);
		myIntent.putExtra("email",email);
		myIntent.putExtra("phone1",phone1);
		myIntent.putExtra("fax",fax);
		myIntent.putExtra("image",image);
		
		//Starting the new activity
		startActivity(myIntent);
	}
	
} // end Directory class