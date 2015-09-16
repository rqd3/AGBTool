package com.shared.models;

import java.io.Serializable;

/**
 * Model for agb_source entries
 * @author rqd3-u
 *
 */
public class AGBSource implements Serializable {

	/**
	 * Represents a agb source f.e. ebay
	 */

	
	private int agbSourceId;
	
	private String name;
	
	private String link;
	
	private String xPath;
	

	public AGBSource(){
		
	}
	
	public AGBSource(int id, String name, String link, String xPath) {
		super();
		this.agbSourceId = id;
		this.name = name;
		this.link = link;
		this.xPath = xPath;
	}

	public int getId() {
		return agbSourceId;
	}

	public void setId(int id) {
		this.agbSourceId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public String getXPath() {
		return xPath;
	}

	public void setXPath(String xPath) {
		this.xPath = xPath;
	}


	@Override
	public String toString() {
		return "AGBSource [id=" + agbSourceId + ", name=" + name + ", link=" + link + ", xPath=" + xPath
				+ "]";
	}
	
	

}
