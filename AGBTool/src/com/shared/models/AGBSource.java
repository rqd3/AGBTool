package com.shared.models;

import java.io.Serializable;

public class AGBSource implements Serializable {

	/**
	 * Represents a agb source f.e. ebay
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String name;
	
	private String link;
	
	private String xPath;
	

	public AGBSource(int id, String name, String link, String xPath) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.xPath = xPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AGBSource [id=" + id + ", name=" + name + ", link=" + link + ", xPath=" + xPath
				+ "]";
	}
	
	

}
