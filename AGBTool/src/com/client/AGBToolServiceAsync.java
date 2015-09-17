package com.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.shared.models.AGBSource;
import com.shared.models.AGBVersion;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface AGBToolServiceAsync {
	
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	
	void getTopTenAGBs( AsyncCallback<List<AGBSource>> callback) throws IllegalArgumentException;
	
	void getAllAGBs( AsyncCallback<List<AGBSource>> callback) throws IllegalArgumentException;
	
	void getAllAGBVersionsOfSource (int sourceId, AsyncCallback<List<AGBVersion>> asyncCallback) throws IllegalArgumentException;
	
	void getLatestAGBVersions( AsyncCallback<List<AGBVersion>> callback) throws IllegalArgumentException;
}
