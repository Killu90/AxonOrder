package com.novomind.config;

import java.net.UnknownHostException;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.mongo.eventsourcing.eventstore.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.MongoTemplate;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.novomind.write.aggregates.Order;
import com.novomind.write.commandhandler.OrderCommandHandler;


@Configuration
public class AxonConfig {
    @Bean
    public CommandBus simpleCommandBus() {
        SimpleCommandBus simpleCommandBus = new SimpleCommandBus();
        simpleCommandBus.registerDispatchInterceptor(new BeanValidationInterceptor<>());
        return simpleCommandBus;
    }

    @Autowired
    private AxonConfiguration axonConfiguration;
    @Autowired
    private MongoTemplate eventStoreMongoTemplate;
    @Autowired
    private org.axonframework.mongo.eventhandling.saga.repository.MongoTemplate sagaMongoTemplate;

    @Bean
    @ConditionalOnMissingBean
    public EventBus eventBus() throws UnknownHostException {
        return new EmbeddedEventStore(eventStorageEngine());
    }

    @Bean
    public MongoEventStorageEngine eventStorageEngine() throws UnknownHostException {
        return new MongoEventStorageEngine(mongoTemplate());
    }

//    @Bean
//    public MongoClient mongo() throws UnknownHostException {
//        return new MongoClient("10.21.94.15", 27017);
//    }

    @Bean
    public MongoClient mongo() throws UnknownHostException {
        return new MongoClient("127.0.0.1", 27017);
    }
    
    @Bean
    public org.axonframework.mongo.eventsourcing.eventstore.MongoTemplate mongoTemplate() throws UnknownHostException {
        return new DefaultMongoTemplate(mongo(), "order", "domainevents", "snapshotevents");
    }

    @Bean
    public MongoSagaStore sagaRepository() {
        return new MongoSagaStore(sagaMongoTemplate);
    }

    @Bean
    public org.axonframework.mongo.eventhandling.saga.repository.MongoTemplate mongoSagaTemplate()
            throws UnknownHostException {
        return new org.axonframework.mongo.eventhandling.saga.repository.DefaultMongoTemplate(mongo(),
                "order",
                "sagas");
    }

    @Bean
    public OrderCommandHandler orderCommandHandler() throws UnknownHostException {
        return new OrderCommandHandler(axonConfiguration.repository(Order.class), eventBus());
    }
    
}
