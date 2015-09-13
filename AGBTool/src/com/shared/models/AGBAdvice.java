package com.shared.models;

import java.sql.Date;

/**
 * Model for agb_advice entries
 * @author rqd3-u
 *
 */
public class AGBAdvice {
	
	int id;
	
	String link;
	
	Date submit_date;
	
	String state;
	
	public AGBAdvice(){
		
	}

	public AGBAdvice(int id, String link, Date submit_date, String state) {
		super();
		this.id = id;
		this.link = link;
		this.submit_date = submit_date;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getSubmit_date() {
		return submit_date;
	}

	public void setSubmit_date(Date submit_date) {
		this.submit_date = submit_date;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "AGBAdvice [id=" + id + ", link=" + link + ", submit_date="
				+ submit_date + ", state=" + state +"]";
	}
	
	

}
