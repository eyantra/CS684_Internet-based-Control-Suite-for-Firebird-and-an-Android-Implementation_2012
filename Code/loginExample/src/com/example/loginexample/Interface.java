package com.example.loginexample;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
 
public class Interface extends Activity {
 
	 
	  private Spinner spinner1, spinner2;
	  private Button btnSubmit;
	  private int i;
	  HttpPost httppost;	
	  StringBuffer buffer;
	    HttpResponse response;
	    HttpClient httpclient;
	    InputStream inputStream;
	    SharedPreferences app_preferences ;
	    List<NameValuePair> nameValuePairs;
	    CheckBox check;
	    byte[] data;
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interface);
	 
		
		

        try {
        	 //Toast.makeText(MainActivity.this, "Trying to connect to server", Toast.LENGTH_LONG).show();
        	
        	httpclient = new DefaultHttpClient();
       	 httppost = new HttpPost("http://www.cse.iitb.ac.in/embedded/bot_id_send.php");
//            // Add your data
//          // nameValuePairs = new ArrayList<NameValuePair>(2);
////           nameValuePairs.add(new BasicNameValuePair("UserEmail", username.trim()));
////            nameValuePairs.add(new BasicNameValuePair("Password", password.trim()));
////            httppost.setEntity(new UrlEncodedFormEntity());
//
//            // Execute HTTP Post Request
           response = httpclient.execute(httppost);
            inputStream = response.getEntity().getContent();
//           // Toast.makeText(MainActivity.this, "getting values", Toast.LENGTH_LONG).show();
           data = new byte[256];
//
            buffer = new StringBuffer();
            int len = 0;
            while (-1 != (len = inputStream.read(data)) )
            {
                buffer.append(new String(data, 0, len));
           }

            inputStream.close();
        }

        catch (Exception e)
        {
            Toast.makeText(Interface.this, "error"+e.toString(), Toast.LENGTH_LONG).show();
       }
		
		addItemsOnSpinner2();
		//addItemsOnSpinner1();
		addListenerOnButton();
		addListenerOnSpinnerItemSelection();
	  }
	 
	  // add items into spinner dynamically
	  public void addItemsOnSpinner2() {
	 
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		//List<Comment> values = datasource.getAllComments();
		//ArrayAdapter<Comment> dataAdapter = new ArrayAdapter<Comment>(this,
		        //android.R.layout.simple_spinner_item, values);
		//List<String> list = new ArrayList<String>();
		//list.add("Task 1");
		//list.add("Task 2");
		//list.add("Task 3");
		
		String returnedItems= buffer.substring(0).toString();
	    String [] strings = returnedItems.split(",");
	    List<String> list = new ArrayList<String>();
		while(i<strings.length){
			list.add(strings[i]);
			i++;
			//list.add("Task 2");
			//list.add("Task 3");
			
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(dataAdapter);
	  }
	 
/*	  public void addItemsOnSpinner1() {
			 i=0;
			spinner1 = (Spinner) findViewById(R.id.spinner1);
			//List<Comment> values = datasource.getAllComments();
			//ArrayAdapter<Comment> dataAdapter = new ArrayAdapter<Comment>(this,
			        //android.R.layout.simple_spinner_item, values);
			//List<String> list = new ArrayList<String>();
			//list.add("Task 1");
			//list.add("Task 2");
			//list.add("Task 3");
			String returnedItems = "d,b,c";
		    String [] strings = returnedItems.split(",");
		    List<String> list = new ArrayList<String>();
			while(i<strings.length){
				list.add(strings[i]);
				i++;
				//list.add("Task 2");
				//list.add("Task 3");
				
			}
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner1.setAdapter(dataAdapter);
		  }
	*/	 
	  
	  public void addListenerOnSpinnerItemSelection() {
		spinner2 = (Spinner) findViewById(R.id.spinner2);
	//	spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	  }
	 
	  // get the selected dropdown list value
	  public void addListenerOnButton() {
	 
		//spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
	 
		btnSubmit.setOnClickListener(new OnClickListener() {
	 
		 
		  public void onClick(View v) {
			  Intent intent = new Intent(Interface.this,Task_id.class);
			  intent.putExtra("Bot_id", String.valueOf(spinner2.getSelectedItem()));
              startActivity(intent);  
//		    Toast.makeText(Interface.this,"OnClickListener : " + 
//	                "\nSpinner 1 : "+ String.valueOf(spinner2.getSelectedItem()),
//				Toast.LENGTH_SHORT).show();
		  }
	 
		});
	  }
	}