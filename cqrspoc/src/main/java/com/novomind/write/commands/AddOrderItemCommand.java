package com.novomind.write.commands;

public class AddOrderItemCommand {

	private String orderId;
	private String orderItemId;
	private Long amount;
	

	public AddOrderItemCommand(String orderId, String orderItemId, Long amount){
		System.out.println(this.getClass().toString() + "### instanciate AddOrderItemCommand");
		this.orderId = orderId;
		this.orderItemId = orderItemId;
		this.amount = amount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}
	
	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
}
