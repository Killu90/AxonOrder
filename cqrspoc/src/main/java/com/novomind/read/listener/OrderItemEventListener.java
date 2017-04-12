package com.novomind.read.listener;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.novomind.read.queryobjects.OrderItemEntry;
import com.novomind.read.queryobjects.OrderQueryObject;
import com.novomind.read.repositories.OrderQueryObjectRepository;
import com.novomind.write.events.OrderItemsAddedToOrderEvent;

@Component
public class OrderItemEventListener {

	private OrderQueryObjectRepository repository;
	
	public OrderItemEventListener(OrderQueryObjectRepository repository){
		System.out.println(this.getClass().toString() + "### instanciate OrderItemEventListener");
		this.repository = repository;
	}
	
    @EventHandler
    public void handleEvent(OrderItemsAddedToOrderEvent event) {
    	System.out.println(this.getClass().toString() + "### handle OrderItemsAddedToOrderEvent");
    	OrderItemEntry itemEntry = createItemEntry(event.getOrderItemId(), event.getAmount());

        OrderQueryObject orderQueryObject = repository.findOne(event.getOrderId());
        orderQueryObject.addOrderItem(itemEntry);

        repository.save(orderQueryObject);
    }
    
    private OrderItemEntry createItemEntry(String identifier, long amount) {
   	 OrderItemEntry itemEntry = new OrderItemEntry(identifier, amount);
        return itemEntry;
    }
	
	
}
