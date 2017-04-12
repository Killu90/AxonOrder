package com.novomind.write.events;

public class OrderCreatedEvent {

	private final String orderId;
	private final String userId;
	private final String eventIndicator = "I am an Event";

	public OrderCreatedEvent(String orderId, String userId) {
		System.out.println(this.getClass().toString() + "### instanciate OrderCreatedEvent");
		this.orderId = orderId;
		this.userId = userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getUserId() {
		return userId;
	}
	
	public String getEventIndicator() {
		return eventIndicator;
	}

}
