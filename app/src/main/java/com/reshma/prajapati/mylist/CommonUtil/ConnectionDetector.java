package com.reshma.prajapati.mylist.CommonUtil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/*
class that checks for availability of internet
 */
public class ConnectionDetector {

	private Context _context;

	public ConnectionDetector(Context context) {
		this._context = context;
	}

	public boolean isConnectingToInternet() {
		ConnectivityManager connectivity = (ConnectivityManager) _context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (NetworkInfo anInfo : info)
					if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}

		}
		return false;
	}
}
