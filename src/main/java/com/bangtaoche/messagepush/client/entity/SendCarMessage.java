package com.bangtaoche.messagepush.client.entity;

public class SendCarMessage {

	private long carId;
	private long sourceId;
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
	@Override
	public String toString() {
		return "SendCarMessage [carId=" + carId + ", sourceId=" + sourceId + "]";
	}
	
}
