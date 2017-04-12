package com.novomind.write.commandhandler;


import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;

import com.novomind.write.aggregates.Order;
import com.novomind.write.commands.AddOrderItemCommand;
import com.novomind.write.commands.CreateOrderCommand;

public class OrderCommandHandler {

    private Repository<Order> repository;

    public OrderCommandHandler(Repository<Order> repository, EventBus eventBus) {
    	System.out.println(this.getClass().toString() + "### instanciate OrderCommandHandler");
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateOrderCommand command) throws Exception {
    	System.out.println(this.getClass().toString() + "### handle CreateOrderCommand");
        repository.newInstance(() -> new Order(command.getOrderId(), command.getUserId()));
    }
    
    @CommandHandler
    public void handleAddOrderItemCommand(AddOrderItemCommand command) {
    	System.out.println(this.getClass().toString() + "### handleAddOrderItemCommand");
    	Aggregate<Order> order = repository.load(command.getOrderId());
        order.execute(aggregateRoot -> {
            aggregateRoot.addItems(command.getOrderItemId(), command.getAmount());
        });
    }

}
