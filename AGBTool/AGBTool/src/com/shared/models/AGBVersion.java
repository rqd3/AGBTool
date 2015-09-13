package com.shared.models;

import java.io.Serializable;
import java.sql.Date;

/**
 * Model for agb_version entries
 * @author rqd3-u
 *
 */
public class AGBVersion implements Serializable {

	private int agbVersionId;
	
	private int agbSourceId;
	
	private String text;
	
	private int version;
	
	private Date publishedAt;
	
	public AGBVersion(){
		
	}

	public AGBVersion(int agbVersionId, int agbSourceId, String text,
			int version, Date publishedAt) {
		super();
		this.agbVersionId = agbVersionId;
		this.agbSourceId = agbSourceId;
		this.text = text;
		this.version = version;
		this.publishedAt = publishedAt;
	}

	public int getAgbVersionId() {
		return agbVersionId;
	}

	public void setAgbVersionId(int agbVersionId) {
		this.agbVersionId = agbVersionId;
	}

	public int getAgbSourceId() {
		return agbSourceId;
	}

	public void setAgbSourceId(int agbSourceId) {
		this.agbSourceId = agbSourceId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	@Override
	public String toString() {
		return "AGBVersion [agbVersionId=" + agbVersionId + ", agbSourceId="
				+ agbSourceId + ", text=" + text + ", version=" + version
				+ ", publishedAt=" + publishedAt + "]";
	}
	
	
}
