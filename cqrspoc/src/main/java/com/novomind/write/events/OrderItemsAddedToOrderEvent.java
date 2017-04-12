package com.novomind.write.events;


public class OrderItemsAddedToOrderEvent {
    private String orderId;
    private String orderItemId;
    private long amount;

    public OrderItemsAddedToOrderEvent(String orderId, String orderItemId, long amount) {
       	System.out.println(this.getClass().toString() + "### Instanciate OrderItemsAddedToOrderEvent");
    	this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.amount = amount;
    }

	public String getOrderId() {
		return orderId;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public long getAmount() {
		return amount;
	}

}
