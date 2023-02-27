package com.cognizant.mfpe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "MEMBERPOLICY")
public class MemberPolicy {
	

	@Id
	private String mid;
	@Column
	private int pid;
	@Column
	private String bid;
	@Column
	private String mname;
	@Column
	private int mage;
	@Column
	private String mgender;
	@Column
	private String mlocation;
	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String subdate;
	@Column
	private int mtenure;
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public int getMage() {
		return mage;
	}
	public void setMage(int mage) {
		this.mage = mage;
	}
	public String getMgender() {
		return mgender;
	}
	public void setMgender(String mgender) {
		this.mgender = mgender;
	}
	public String getMlocation() {
		return mlocation;
	}
	public void setMlocation(String mlocation) {
		this.mlocation = mlocation;
	}
	public String getSubdate() {
		return subdate;
	}
	public void setSubdate(String subdate) {
		this.subdate = subdate;
	}
	public int getMtenure() {
		return mtenure;
	}
	public void setMtenure(int mtenure) {
		this.mtenure = mtenure;
	}
	
	public MemberPolicy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberPolicy(String mid, int pid, String bid, String mname, int mage, String mgender, String mlocation,
			String string, int mtenure) {
		super();
		this.mid = mid;
		this.pid = pid;
		this.bid = bid;
		this.mname = mname;
		this.mage = mage;
		this.mgender = mgender;
		this.mlocation = mlocation;
		this.subdate = string;
		this.mtenure = mtenure;
	}


}
