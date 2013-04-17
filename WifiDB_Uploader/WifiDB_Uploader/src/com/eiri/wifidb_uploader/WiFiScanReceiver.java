package com.eiri.wifidb_uploader;

import java.util.List;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.util.Log;


public class WiFiScanReceiver extends BroadcastReceiver {
  private static final String TAG = "WiFiScanReceiver";
  WiFiDemo wifiDemo;

  public WiFiScanReceiver(WiFiDemo wifiDemo) {
    super();
    this.wifiDemo = wifiDemo;
  }

  @Override
  public void onReceive(Context c, Intent intent) {
	  
    List<ScanResult> results = wifiDemo.wifi.getScanResults();

    for (ScanResult result : results) {
    	String bssid = result.BSSID;
    	String ssid = result.SSID;
    	int freq = result.frequency;
    	String capabilities = result.capabilities;
    	int level = result.level;
    	Log.d(TAG, "onReceive() message:1");
    	WifiDB post = new WifiDB();
    	post.postLiveData(ssid, bssid, capabilities, freq, level, "", "");
    	
    }

  }

public static String getTag() {
	return TAG;
}
}