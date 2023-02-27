package com.cognizant.mfpe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Policy {
	
	@Id
	private int pid;
	@Column
	private String ptype;
	@Column
	private double capamount;
	@Column
	private double premium;

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public double getCapamount() {
		return capamount;
	}
	public void setCapamount(double capamount) {
		this.capamount = capamount;
	}
	public double getPremium() {
		return premium;
	}
	public void setPremium(double premium) {
		this.premium = premium;
	}
	public Policy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Policy(int pid, String ptype, double capamount, double premium) {
		super();
		this.pid = pid;
		this.ptype = ptype;
		this.capamount = capamount;
		this.premium = premium;
	}
	
	
}
