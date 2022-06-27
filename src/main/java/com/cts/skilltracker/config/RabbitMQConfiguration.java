package com.cts.skilltracker.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

	@Autowired
	RabbitMQProperties rmqProp;

	@Bean
	Queue defaultStream() {
		return new Queue(rmqProp.getQueue(), true);
	}
	
	@Bean
    FanoutExchange eventBusExchange() {
        return new FanoutExchange(rmqProp.getExchange(), true, false);
    }

    @Bean
    Binding binding() {
        return new Binding(rmqProp.getQueue(), Binding.DestinationType.QUEUE, rmqProp.getExchange(), "*.*", null);
    }

    @Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(rmqProp.getHost());
        cachingConnectionFactory.setUsername(rmqProp.getUsername());
        cachingConnectionFactory.setPassword(rmqProp.getPassword());
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
