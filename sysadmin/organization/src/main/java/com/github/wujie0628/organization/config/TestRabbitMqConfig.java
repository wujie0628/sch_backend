package com.github.wujie0628.organization.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TestRabbitMqConfig {
    public static final String EXCHEANGE_NAME="wj-exchange";
    public static final String QUEUE_NAME_1 = "wj-queue1";
    public static final String QUEUE_NAME_2 = "wj-queue2";
    public static final String ROUTE_KEY_1 = "wj.key";
    public static final String ROUTE_KEY_2 = "wj.#";

    @Bean
    public Queue topicQueue1() {
        return new Queue(QUEUE_NAME_1);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(QUEUE_NAME_2);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHEANGE_NAME);
    }

    @Bean
    public Binding bindTopicExchange1() {
        return BindingBuilder
                .bind(topicQueue1())
                .to(topicExchange())
                .with(ROUTE_KEY_1);

    }

    @Bean
    public Binding bindTopicExchange2() {
        return BindingBuilder
                .bind(topicQueue2())
                .to(topicExchange())
                .with(ROUTE_KEY_2);

    }
}
