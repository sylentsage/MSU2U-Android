// ViewContact.java
// Activity for viewing a single contact.
package com.android.msu2u;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewContact extends Activity 
{
	private long rowID; // selected contact's name
	private TextView nameTextView; // displays contact's name 
	private TextView phoneTextView; // displays contact's phone
	private TextView emailTextView; // displays contact's email
	private TextView departmentTextView; // displays contact's street
	private TextView faxTextView; // displays contact's city/state/zip
	private ImageView photoId;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewcontact);
		
		nameTextView = (TextView) findViewById(R.id.nameEditText);
		phoneTextView = (TextView) findViewById(R.id.phone1EditText);
		emailTextView =  (TextView) findViewById(R.id.emailEditText);
		departmentTextView =  (TextView) findViewById(R.id.DepartmentEditText);
		faxTextView =  (TextView) findViewById(R.id.faxEditText);
		photoId = (ImageView)findViewById(R.id.photoImageView);
		
		PictureGetter get;
		get = new PictureGetter();

		//Get our intent from the previous activity
		Intent i = getIntent();
		String fname = i.getStringExtra("fname");
		String department = i.getStringExtra("department");
		String email = i.getStringExtra("email");
		String phone = i.getStringExtra("phone1");
		String fax = i.getStringExtra("fax");
		String pictureURL = i.getStringExtra("image");
		
		//Setting the imageview
		get.execute(pictureURL);
		
		//Setting the text views
		nameTextView.setText(fname);
		phoneTextView.setText(phone);
		emailTextView.setText(email);
		departmentTextView.setText(department);
		faxTextView.setText(fax);
	}
	
	private class PictureGetter extends AsyncTask<String, Void, Void>
	{
		protected Void doInBackground(String... params) {
			//Section loads up the image that shows the staff members photo
			try {
				  Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(params[0]).getContent()); 
				} catch (MalformedURLException e) {
				  e.printStackTrace();
				} catch (IOException e) {
				  e.printStackTrace();
				}
			return null;
		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPreExecute()
		 */
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

	
	}

}