package com.novomind.write.aggregates;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import java.util.HashMap;
import java.util.Map;

import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.novomind.write.events.OrderCreatedEvent;
import com.novomind.write.events.OrderItemsAddedToOrderEvent;

@Aggregate
public class Order {

	@AggregateIdentifier
	private String orderId;
	private String userId;
	private Map<String, Long> orderItems = new HashMap<>();
	
	public Order(){
		
	}

	public Order(String orderId, String userId) {
		System.out.println(this.getClass().toString() + "### apply OrderCreatedEvent");
		apply(new OrderCreatedEvent(orderId, userId));
	}
	
	
    public void addItems(String orderItemId, long amount) {
    	System.out.println(this.getClass().toString() + "### apply OrderItemsAddedToOrderEvent");
        apply(new OrderItemsAddedToOrderEvent(orderId, orderItemId, amount));
    }

	@EventSourcingHandler
	public void on(OrderCreatedEvent event){
		System.out.println(this.getClass().toString() + "### handle OrderCreatedEvent");
		this.orderId = event.getOrderId();
		this.userId = event.getUserId();
	}

	@EventSourcingHandler
    public void onOrderItemsAddedToOrder(OrderItemsAddedToOrderEvent event) {
		System.out.println(this.getClass().toString() + "### handle onOrderItemsAddedToOrder");
        long available = obtainCurrentAvailableItems(event.getOrderId());
        orderItems.put(event.getOrderId(), available + event.getAmount());
    }
	
    private long obtainCurrentAvailableItems(String orderId) {
        long available = 0;
        if (orderItems.containsKey(orderId)) {
            available = orderItems.get(orderId);
        }
        return available;
    }
	
}
