package com.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.shared.models.AGBSource;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface AGBToolServiceAsync {
	
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	
	void getTopTenAGBs( AsyncCallback<List<AGBSource>> callback) throws IllegalArgumentException;
}
