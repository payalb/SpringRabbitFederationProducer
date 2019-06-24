package com.example.demo;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringRabbitFederationProducerApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(SpringRabbitFederationProducerApplication.class, args);
		RabbitTemplate template=ctx.getBean(RabbitTemplate.class);
		template.send("fed.exchange","fed.queue",new Message("hello".getBytes(), new MessageProperties()) );
	}

	@RabbitListener(queues="fed.queue")
	public void receive(Message message) {
		System.out.println("Received message"+ message);
	}
}
