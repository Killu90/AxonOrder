package com.novomind.read.listener;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.novomind.read.queryobjects.OrderQueryObject;
import com.novomind.read.repositories.OrderQueryObjectRepository;
import com.novomind.write.events.OrderCreatedEvent;

@Component
public class OrderQueryObjectListener {

	private final OrderQueryObjectRepository repository;

	public OrderQueryObjectListener(OrderQueryObjectRepository repository) {
		System.out.println(this.getClass().toString() + "### isntanciate OrderQueryObjectListener");
		this.repository = repository;
	}

	@EventHandler
	public void on(OrderCreatedEvent event) {
		System.out.println(this.getClass().toString() + "### handle OrderCreatedEvent");
		repository.save(new OrderQueryObject(event.getOrderId(), event.getUserId()));
	}

}
