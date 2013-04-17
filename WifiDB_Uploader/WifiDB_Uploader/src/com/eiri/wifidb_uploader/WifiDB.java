package com.eiri.wifidb_uploader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class WifiDB {
	public void postLiveData(String sSID, String bSSID, String capabilities, Integer frequency, Integer level, String latitude_str, String longitude_str) {
	    // Create a new HttpClient and Post Header
	    DefaultHttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://dev01.wifidb.net/wifidb/api/live.php");
	    // Add your data
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("ssid", sSID));
        nameValuePairs.add(new BasicNameValuePair("mac", bSSID));
        nameValuePairs.add(new BasicNameValuePair("capabilities", capabilities));
        nameValuePairs.add(new BasicNameValuePair("radio", Integer.toString(frequency)));
        nameValuePairs.add(new BasicNameValuePair("signal", Integer.toString(level)));
        nameValuePairs.add(new BasicNameValuePair("latitude", latitude_str));
        nameValuePairs.add(new BasicNameValuePair("longitude", longitude_str));
        
        try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Execute HTTP Post Request
        try {
			HttpResponse response = httpclient.execute(httppost);
			if (response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
				Log.d("", "HTTP Receive message: " + EntityUtils.toString(entity));
            }			

			
	    } catch (UnsupportedEncodingException uee) {
	        Log.d("Exceptions", "UnsupportedEncodingException");
	        uee.printStackTrace();
	    } catch (ClientProtocolException cpe) {
	        Log.d("Exceptions", "ClientProtocolException");
	        cpe.printStackTrace();
	    } catch (IOException ioe) {
	        Log.d("Exceptions", "IOException");
	        ioe.printStackTrace();
	    }  
	} 
	// see http://androidsnippets.com/executing-a-http-post-request-with-httpclient
}