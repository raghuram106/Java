package com.cognizant.mfpe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Benefits {
	@Id
	int id;
	@Column
	String bid;
	@Column
	String benifit;

	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBenifit() {
		return benifit;
	}
	public void setBenifit(String benifit) {
		this.benifit = benifit;
	}
	public Benefits() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Benefits(int id, String bid, String benifit) {
		super();
		this.id = id;
		this.bid = bid;
		this.benifit = benifit;
	}
	
	

}
