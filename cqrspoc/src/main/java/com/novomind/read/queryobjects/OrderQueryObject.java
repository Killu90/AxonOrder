package com.novomind.read.queryobjects;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class OrderQueryObject {

	@Id
	private String orderId;
	private String userId;
	private String queryObjectIdentifier = "I am a QueryObject (DTO)";

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "ORDER_ITEM", joinColumns = @JoinColumn(name = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "ORDERITEM_ID"))
	private Map<String, OrderItemEntry> orderItems = new HashMap<>();
	
	public OrderQueryObject(String orderId, String userId) {
		System.out.println(this.getClass().toString() + "### Instanciate OrderQueryObject");
		this.orderId = orderId;
		this.userId = userId;
	}
	
	public OrderQueryObject(){
		
	}

	public String getOrderId() {
		return orderId;
	}

	public String getUserId() {
		return userId;
	}

	public String getQueryObjectIdentifier() {
		return queryObjectIdentifier;
	}
	
    public Map<String, OrderItemEntry> getOrderItems() {
        return orderItems;
    }
    
	public void addOrderItem(OrderItemEntry orderItem){
		handleAdd(orderItems, orderItem);
	}
	
    private void handleAdd(Map<String, OrderItemEntry> items, OrderItemEntry itemEntry) {
        if (items.containsKey(itemEntry.getOrderItemId())) {
            OrderItemEntry foundEntry = items.get(itemEntry.getOrderItemId());
            foundEntry.setAmount(foundEntry.getAmount() + itemEntry.getAmount());
        } else {
            items.put(itemEntry.getOrderItemId(), itemEntry);
        }
    }
	
}
