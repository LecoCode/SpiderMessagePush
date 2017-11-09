package com.bangtaoche.messagepush.client.entity;

import com.darengong.tools.dao.annotation.Column;
import com.darengong.tools.dao.annotation.Id;
import com.darengong.tools.dao.annotation.Table;

import java.util.Date;

@Table(name = "tbl_che168_price_change")
public class Che168PriceChange {

	/**
	 * 主键ID
	 */
	@Id
	private long id;
	
	/**
	 * 车辆ID
	 */
	@Column(name = "car_id")
	private long carId;
	
	/**
	 * 添加时间
	 */
	@Column(name = "add_time")
	private Date addTime;
	
	/**
	 * 车辆价格
	 */
	@Column(name = "car_price")
	private double carPrice;

	@Override
	public String toString() {
		return "RRCPriceChange [id=" + id + ", carId=" + carId + ", addTime=" + addTime + ", carPrice=" + carPrice
				+ "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public double getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(double carPrice) {
		this.carPrice = carPrice;
	}
}
