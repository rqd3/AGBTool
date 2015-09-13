package com.server;

import java.util.List;

import com.client.AGBToolService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.server.backend.APIController;
import com.shared.FieldVerifier;
import com.shared.models.AGBSource;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class AGBToolServiceImpl extends RemoteServiceServlet implements
		AGBToolService {

	public String greetServer(String input) throws IllegalArgumentException {
		System.out.println("backend");

		//testing
		//DBDriver dbDriver = new DBDriver();
		//APIController apiController = new APIController();
		
		
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}
	
	/**
	 * 
	 */
	public List<AGBSource> getTopTenAGBs() throws IllegalArgumentException{
		APIController apiController = new APIController();
		
		return apiController.getAllAGBSources();
		
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
