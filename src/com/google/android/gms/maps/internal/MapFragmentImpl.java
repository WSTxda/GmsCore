package com.google.android.gms.maps.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.GoogleMapOptions;

public class MapFragmentImpl extends IMapFragmentDelegate.Stub {

	private GoogleMapImpl map;
	private GoogleMapOptions options;
	private Context context;

	public MapFragmentImpl(Activity activity) {
		context = activity;
	}

	private GoogleMapImpl myMap() {
		if (map == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			map = new GoogleMapImpl(inflater, options);
		}
		return map;
	}

	@Override
	public IGoogleMapDelegate getMap() throws RemoteException {
		return myMap().getDelegate();
	}

	@Override
	public void onInflate(IObjectWrapper activity, GoogleMapOptions options, Bundle savedInstanceState) throws RemoteException {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) throws RemoteException {
		myMap().onCreate(savedInstanceState);
	}

	@Override
	public IObjectWrapper onCreateView(IObjectWrapper layoutInflater, IObjectWrapper container, Bundle savedInstanceState) throws RemoteException {
		if (map == null) {
			LayoutInflater inflater = (LayoutInflater) ObjectWrapper.unwrap(layoutInflater);
			map = new GoogleMapImpl(inflater, options);
			map.onCreate(savedInstanceState);
		} else {
			View view = map.getView();
			if (view.getParent() instanceof ViewGroup) {
				((ViewGroup) view.getParent()).removeView(view);
			}
		}
		return ObjectWrapper.wrap(myMap().getView());
	}

	@Override
	public void onResume() throws RemoteException {

	}

	@Override
	public void onPause() throws RemoteException {

	}

	@Override
	public void onDestroyView() throws RemoteException {

	}

	@Override
	public void onDestroy() throws RemoteException {

	}

	@Override
	public void onLowMemory() throws RemoteException {

	}

	@Override
	public void onSaveInstanceState(Bundle outState) throws RemoteException {

	}

	@Override
	public boolean isReady() throws RemoteException {
		return false;
	}
}
