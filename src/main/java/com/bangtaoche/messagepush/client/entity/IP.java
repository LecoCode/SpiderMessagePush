package com.bangtaoche.messagepush.client.entity;

import com.darengong.tools.dao.annotation.Column;
import com.darengong.tools.dao.annotation.Id;
import com.darengong.tools.dao.annotation.Table;

@Table(name="tbl_ip")
public class IP {

	@Id
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name="ip")
	private String IP;
	@Column(name="port")
	private int port;
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return "IP [id=" + id + ", IP=" + IP + ", port=" + port + "]";
	}
	
}
