package com.novomind.api;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.novomind.read.queryobjects.OrderQueryObject;
import com.novomind.read.repositories.OrderQueryObjectRepository;
import com.novomind.write.commands.AddOrderItemCommand;
import com.novomind.write.commands.CreateOrderCommand;

@RestController
public class OrderApi {
	private OrderQueryObjectRepository repository;
	private CommandGateway commandGateway;

	public OrderApi(OrderQueryObjectRepository repository, CommandGateway commandGateway) {
		this.repository = repository;
		this.commandGateway = commandGateway;
	}

	@PostMapping("/create")
	public CompletableFuture<String> createOrder(@RequestBody Map<String, String> request) {
		System.out.println("send CreateOrderCommand To CommandGateway");
		String id = UUID.randomUUID().toString();
		return commandGateway.send(new CreateOrderCommand(id, request.get("orderId"), request.get("userId")));
	}
	
	@PostMapping("/addItem")
	public CompletableFuture<String> addItem(@RequestBody Map<String, String> request) {
		System.out.println("### send AddOrderItemCommand To Command Gateway");
		return commandGateway.send(new AddOrderItemCommand(request.get("orderId"), request.get("orderItemId"),
				Long.parseLong(request.get("amount"))));
	}

	@GetMapping("/all")
	public List<OrderQueryObject> findAll() {
		return repository.findAll();
	}

	@GetMapping("/{orderId}")
	public OrderQueryObject find(@PathVariable String orderId) {
		return repository.findOne(orderId);
	}

}
