package com.junie.juojbackendjudgeservice.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: RabbitMQ配置类（解决重复创建Connections问题）
 * @Author: juheng
 * @Create: 2026-04-27
 * @Version: 1.0
 */
@Configuration
public class RabbitMqConfig {

    // 只要定义了 Bean，Spring 启动连接上 RabbitMQ 后会自动发现并创建它们
    @Bean
    public DirectExchange codeExchange() {
        return new DirectExchange("code_exchange", true, false);
    }

    @Bean
    public Queue codeQueue() {
        return new Queue("code_queue", true);
    }

    @Bean
    public Binding codeBinding(Queue codeQueue, DirectExchange codeExchange) {
        return BindingBuilder.bind(codeQueue).to(codeExchange).with("my_routingKey");
    }
}
