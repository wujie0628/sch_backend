package com.github.wujie0628.organization.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "wj-queue1")
@Slf4j
public class Hello1Receive {
    @RabbitHandler
    public void consumer(String message) {
        System.out.println("hello1---->"+ message);
    }
}
