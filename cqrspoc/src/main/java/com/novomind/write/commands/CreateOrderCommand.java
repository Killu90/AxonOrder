package com.novomind.write.commands;

public class CreateOrderCommand {

	private String id;
	private String orderId;
	private String userId;
	private String orderItemId;
	
	public CreateOrderCommand(String id, String orderId, String userId) {
		System.out.println(this.getClass().toString() + "### instanciate CreateOrderCommand");
		this.id = id;
		this.orderId = orderId;
		this.userId = userId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	public String getOrderItemId() {
		return orderItemId;
	}


	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}


	public String getUserId() {
		return userId;
	}

	public String getOrderId() {
		return orderId;
	}
	
	public String getId() {
		return id;
	}
	
	
	
}
