package com.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.shared.models.AGBSource;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("agbtool")
public interface AGBToolService extends RemoteService {
	
	String greetServer(String name) throws IllegalArgumentException;
	
	List<AGBSource> getTopTenAGBs() throws IllegalArgumentException;
}
