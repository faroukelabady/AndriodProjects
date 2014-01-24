/**
 * 
 */
package com.solutions.serviceDiscovery;

import android.net.nsd.NsdManager;
import android.net.nsd.NsdManager.DiscoveryListener;
import android.net.nsd.NsdManager.ResolveListener;
import android.net.nsd.NsdServiceInfo;
import android.util.Log;

/**
 * @author farouk
 * 
 */
public class ServiceDiscovery implements DiscoveryListener {

	public static final String SERVICE_TYPE = "_7elol-remote._tcp.local.";

	public static final String TAG = "NsdHelper";
	public String mServiceName = "7elol_remote_home";
	NsdManager mNsdManager;
	ResolveListener mResolveListener;

	// Called as soon as service discovery begins.
	@Override
	public void onDiscoveryStarted(String regType) {
		Log.d(TAG, "Service discovery started");
	}

	@Override
	public void onServiceFound(NsdServiceInfo service) {
		// A service was found! Do something with it.
		Log.d(TAG, "Service discovery success" + service);
		if (!service.getServiceType().equals(SERVICE_TYPE)) {
			// Service type is the string containing the protocol and
			// transport layer for this service.
			Log.d(TAG, "Unknown Service Type: " + service.getServiceType());
		} else if (service.getServiceName().equals(mServiceName)) {
			// The name of the service tells the user what they'd be
			// connecting to. It could be "Bob's Chat App".
			Log.d(TAG, "Same machine: " + mServiceName);
		} else if (service.getServiceName().contains("NsdChat")) {
			mNsdManager.resolveService(service, mResolveListener);
		}
	}

	@Override
	public void onServiceLost(NsdServiceInfo service) {
		// When the network service is no longer available.
		// Internal bookkeeping code goes here.
		Log.e(TAG, "service lost" + service);
	}

	@Override
	public void onDiscoveryStopped(String serviceType) {
		Log.i(TAG, "Discovery stopped: " + serviceType);
	}

	@Override
	public void onStartDiscoveryFailed(String serviceType, int errorCode) {
		Log.e(TAG, "Discovery failed: Error code:" + errorCode);
		mNsdManager.stopServiceDiscovery(this);
	}

	@Override
	public void onStopDiscoveryFailed(String serviceType, int errorCode) {
		Log.e(TAG, "Discovery failed: Error code:" + errorCode);
		mNsdManager.stopServiceDiscovery(this);
	}

}
