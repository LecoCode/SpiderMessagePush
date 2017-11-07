package com.bangtaoche.messagepush.client.entity;

public class SendPriceMessage {
	
	private long carId;
	private long sourceId;
	private double price;
	private double priceChange;
	public long getCarId() {
		return carId;
	}
	public void setCarId(long carId) {
		this.carId = carId;
	}
	public long getSourceId() {
		return sourceId;
	}
	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPriceChange() {
		return priceChange;
	}
	public void setPriceChange(double priceChange) {
		this.priceChange = priceChange;
	}
	@Override
	public String toString() {
		return "SendPriceMessage [carId=" + carId + ", sourceId=" + sourceId + ", price=" + price + ", priceChange="
				+ priceChange + "]";
	}
	
	
	
}
