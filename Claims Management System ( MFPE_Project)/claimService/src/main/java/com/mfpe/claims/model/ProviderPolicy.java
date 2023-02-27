package com.mfpe.claims.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROVIDERPOLICY")
public class ProviderPolicy {
	
	@Id
	@Column
	private String hospitalId;
	@Column
	private int pid;
	@Column
	private String hospitalName;
	@Column
	private String location;
	public String getHospitalId() {
		return hospitalId;
		
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getHospitalname() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalname) {
		this.hospitalName = hospitalname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public ProviderPolicy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProviderPolicy( String hospitalId, int pid, String hospitalName, String location) {
		super();
	
		this.hospitalId = hospitalId;
		this.pid = pid;
		this.hospitalName = hospitalName;
		this.location = location;
	}
}
