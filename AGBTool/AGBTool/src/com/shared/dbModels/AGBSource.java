package com.shared.dbModels;

import java.io.Serializable;

public class AGBSource implements Serializable {

	/**
	 * Represents a agb source f.e. ebay
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String name;
	
	private String link;
	

	public AGBSource(int id, String name, String link) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AGBSource [id=" + id + ", name=" + name + ", link=" + link
				+ "]";
	}
	
	

}
