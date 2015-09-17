package com.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.shared.models.AGBSource;
import com.shared.models.AGBVersion;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("agbtoolservice")
public interface AGBToolService extends RemoteService {
	
	String greetServer(String name) throws IllegalArgumentException;
	
	List<AGBSource> getTopTenAGBs() throws IllegalArgumentException;
	
	List<AGBSource> getAllAGBs() throws IllegalArgumentException;
	
	List<AGBVersion> getAllAGBVersionsOfSource(int sourceId) throws IllegalArgumentException;
	
	List<AGBVersion> getLatestAGBVersions() throws IllegalArgumentException;
}
