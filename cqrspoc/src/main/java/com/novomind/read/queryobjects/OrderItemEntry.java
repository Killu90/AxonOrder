package com.novomind.read.queryobjects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderItemEntry {
	
	@Id
	private String orderItemId;
	private Long amount;
	
	public OrderItemEntry(){
		
	}
	
	public OrderItemEntry(String orderItemId, Long amount){
		System.out.println(this.getClass().toString() + "### Instanciate OrderItemEntry");
		this.orderItemId = orderItemId;
		this.amount = amount;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	
	
}
