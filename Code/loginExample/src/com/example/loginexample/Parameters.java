//package com.example.loginexample;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
////import org.apache.*;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//
//import android.os.Bundle;
//import android.app.Activity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.Spinner;
//import android.widget.Toast;
//import android.support.v4.app.NavUtils;
//import android.os.Bundle;
//import android.app.Activity;
//import android.content.Intent;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.support.v4.app.NavUtils;
//import android.support.v4.view.ViewPager.LayoutParams;
//import android.text.Layout;

//
//public class Parameters extends Activity {
//	
//private LinearLayout mLayout;
//private EditText mEditText;
//private Button mButton;	
//private LinearLayout myLinearLayout;
//HttpPost httppost;	
//StringBuffer buffer;
//  HttpResponse response;
//  HttpClient httpclient;
//  InputStream inputStream;
//  SharedPreferences app_preferences ;
//  List<NameValuePair> nameValuePairs;
//  CheckBox check;
//  byte[] data;
//  @Override 
//  public void onCreate(Bundle savedInstanceState) {
//	super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_parameters);
//   mLayout = (LinearLayout) findViewById(R.id.linearLayout);
//   mEditText = (EditText) findViewById(R.id.editText);
//   mButton = (Button) findViewById(R.id.button);
//   
//    mButton.setOnClickListener(onClick());
//    TextView textView = new TextView(this);
//    textView.setText("New text");
//}
//}
//
//private OnClickListener onClick(){
//    return new OnClickListener() {
//
//      
//        public void onClick(View v) {
//          
//		
//			mLayout.addView(createNewTextView(mEditText.getText().toString()));
//        }
//    };
//}
////
////private TextView createNewTextView(String text) {
////	final LayoutParams lparams = new LayoutParams();
////    final TextView textView = new TextView(this);
////    textView.setLayoutParams(lparams);
////    textView.setText("New text: " + text);
////    return textView;
////}
////}
package com.example.loginexample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Parameters extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}