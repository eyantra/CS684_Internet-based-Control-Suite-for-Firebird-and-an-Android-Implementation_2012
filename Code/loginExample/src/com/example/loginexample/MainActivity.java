package com.example.loginexample;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 

public class MainActivity extends Activity {

	private EditText etUsername;
	private EditText etPassword;
	private Button btnLogin;
	private Button btnCancel;
	private TextView lblResult;
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
        setContentView(R.layout.activity_main);
     // Get the EditText and Button References
       etUsername = (EditText)findViewById(R.id.username);
       etPassword = (EditText)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.login_button);
        btnCancel = (Button)findViewById(R.id.cancel_button);
        lblResult = (TextView)findViewById(R.id.result);
    
        
    
    

    btnLogin.setOnClickListener(new OnClickListener() {
       
        public void onClick(View v) {
            // Check Login
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
             
//            if(username.equals("g") && password.equals("g")){
//                //lblResult.setText("Login successful.");
//                Intent intent = new Intent(MainActivity.this, Interface.class);
//                startActivity(intent);  
//                
//            } else {
//                lblResult.setText("Login failed. Username and/or password doesn't match.");
//            }
            
            if(username.equals("") && password.equals(""))
            {
                 Toast.makeText(MainActivity.this, "Blank Field..Please Enter", Toast.LENGTH_LONG).show();
            }
            else
            {


            try {
            	 //Toast.makeText(MainActivity.this, "Trying to connect to server", Toast.LENGTH_LONG).show();
            	 httpclient = new DefaultHttpClient();
            	 httppost = new HttpPost("http://www.cse.iitb.ac.in/embedded/main.php");
                // Add your data
               nameValuePairs = new ArrayList<NameValuePair>(2);
               nameValuePairs.add(new BasicNameValuePair("UserEmail", username.trim()));
                nameValuePairs.add(new BasicNameValuePair("Password", password.trim()));
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
                Toast.makeText(MainActivity.this, "error"+e.toString(), Toast.LENGTH_LONG).show();
           }
            	//buffer = new StringBuffer();
            	//buffer.append('Y');
            	
            if(buffer.charAt(0)=='Y')
            {
                Toast.makeText(MainActivity.this, "login successfull", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, Interface.class);
             startActivity(intent);  
          }
           else
            {
                Toast.makeText(MainActivity.this, "Invalid Username or password", Toast.LENGTH_LONG).show();
          }
            }
        }
    });
    btnCancel.setOnClickListener(new OnClickListener() { public void onClick(View v) {finish();} });

}

    

    
}