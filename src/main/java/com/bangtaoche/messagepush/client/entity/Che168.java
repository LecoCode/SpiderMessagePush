package com.bangtaoche.messagepush.client.entity;

import com.darengong.tools.dao.annotation.Column;
import com.darengong.tools.dao.annotation.Id;
import com.darengong.tools.dao.annotation.Table;

import java.util.Date;

@Table(name = "tbl_che168")
public class Che168 {

	@Id
	private long id;
	
	@Column(name = "city_name")
	private String cityName;
	
	@Column(name = "city_url")
	private String cityUrl;

	@Column(name = "add_time")
    private Date addTime;

	@Column(name = "car_url")
	private String carUrl;
	
	@Column(name = "new_status")
	private int newStatus;
	
	@Column(name = "price_reduction")
	private int priceReduction;
	
	@Column(name = "car_price")
	private double carPrice;
	
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityUrl() {
		return cityUrl;
	}

	public void setCityUrl(String cityUrl) {
		this.cityUrl = cityUrl;
	}

    public String getCarUrl() {
		return carUrl;
	}

	public void setCarUrl(String carUrl) {
		this.carUrl = carUrl;
	}

	public int getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(int newStatus) {
		this.newStatus = newStatus;
	}

	public int getPriceReduction() {
		return priceReduction;
	}

	public void setPriceReduction(int priceReduction) {
		this.priceReduction = priceReduction;
	}

	public double getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(double carPrice) {
		this.carPrice = carPrice;
	}

	@Override
	public String toString() {
		return "CityUrl [id=" + id + ", cityName=" + cityName + ", cityUrl=" + cityUrl + ", addTime=" + addTime
				+ ", carUrl=" + carUrl + ", newStatus=" + newStatus + ", priceReduction=" + priceReduction
				+ ", carPrice=" + carPrice + "]";
	}
}
