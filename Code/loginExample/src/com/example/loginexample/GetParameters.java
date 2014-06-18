package com.example.loginexample;

import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
//import org.apache.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.R.id;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager.LayoutParams;
import android.text.Layout;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class GetParameters extends Activity {
	private LinearLayout mLayout;
	private EditText mEditText;
	private Button mButton;	
	
	HttpPost httppost;	
	StringBuffer buffer;
	  HttpResponse response;
	  HttpClient httpclient;
	  InputStream inputStream;
	  SharedPreferences app_preferences ;
	  List<NameValuePair> nameValuePairs;
	  CheckBox check;
	  byte[] data;
	  public int stringArrayLength;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_parameters);
       // getActionBar().setDisplayHomeAsUpEnabled(true);
        try {
        	        	
        	httpclient = new DefaultHttpClient();
       	 httppost = new HttpPost("http://www.cse.iitb.ac.in/embedded/param_send.php");
            // Add your data
           nameValuePairs = new ArrayList<NameValuePair>(2);
          nameValuePairs.add(new BasicNameValuePair("bot_id", getIntent().getExtras().getString("Bot_id").trim()));
          nameValuePairs.add(new BasicNameValuePair("task_id", getIntent().getExtras().getString("Task_id").trim()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

           // Execute HTTP Post Request
           response = httpclient.execute(httppost);
            inputStream = response.getEntity().getContent();
           // Toast.makeText(MainActivity.this, "getting values", Toast.LENGTH_LONG).show();
           data = new byte[256];

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
            Toast.makeText(GetParameters.this, "error"+e.toString(), Toast.LENGTH_LONG).show();
       }
        String returnedItems = buffer.substring(0).toString();
	    String [] strings = returnedItems.split(",");
	    stringArrayLength=strings.length;
        mLayout = (LinearLayout) findViewById(R.id.myLinearLayout);
       
       for (int j = 2; j<strings.length; j++) {
    	   EditText et = new EditText(this);
    	   et.setMinimumWidth(500);
           et.setMinLines(1);
           et.setMaxLines(3);
           et.setId(j);   
           Toast.makeText(GetParameters.this, strings[j], Toast.LENGTH_LONG).show();
         TextView textView = new TextView(this);
        textView.setText(strings[j]+":");
        mLayout.addView(textView);
        mLayout.addView(et);
       }
       Button buttonSubmit = new Button(this);
       buttonSubmit.setText("Submit");
       buttonSubmit.setLayoutParams(new LayoutParams());
       buttonSubmit.setId(1);
      
       mLayout.addView(buttonSubmit);
      buttonSubmit.setOnClickListener(new OnClickListener() {
          
           public void onClick(View v) {

        	   nameValuePairs = new ArrayList<NameValuePair>(7);
        	   nameValuePairs.add(new BasicNameValuePair("bot_id", getIntent().getExtras().getString("Bot_id").trim()));
               nameValuePairs.add(new BasicNameValuePair("task_id", getIntent().getExtras().getString("Task_id").trim()));
        	   for (int j=2; j<stringArrayLength-1; j++) {
        		String paramName = "t"+ (j-1);
        		Toast.makeText(GetParameters.this, paramName, Toast.LENGTH_LONG).show();
        				EditText etxt = (EditText) findViewById(j);
        		   nameValuePairs.add(new BasicNameValuePair(paramName, etxt.getText().toString().trim())); 
        	   }
 

//               if(username.equals("") && password.equals(""))
//               {
//                    Toast.makeText(MainActivity.this, "Blank Field..Please Enter", Toast.LENGTH_LONG).show();
//               }
//               else
//               {
//
//
               try {
               	 //Toast.makeText(GetParameters.this, "Trying to connect to server", Toast.LENGTH_LONG).show();
               	 httpclient = new DefaultHttpClient();
               	 httppost = new HttpPost("http://www.cse.iitb.ac.in/embedded/message_pass.php");
                   // Add your data
                 
//                 nameValuePairs.add(new BasicNameValuePair("UserEmail", username.trim()));
//                   nameValuePairs.add(new BasicNameValuePair("Password", password.trim()));
                   httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//                   // Execute HTTP Post Request
                   response = httpclient.execute(httppost);
               }
              catch (Exception e)
               {
                   Toast.makeText(GetParameters.this, "error"+e.toString(), Toast.LENGTH_LONG).show();
              }

          }
    });
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_get_parameters, menu);
        return true;
    }

    
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
	//message_pass.php, bot_id, task_id, t1-t5

}
