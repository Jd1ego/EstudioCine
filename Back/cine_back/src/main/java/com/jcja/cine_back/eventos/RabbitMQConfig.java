package com.jcja.cine_back.eventos;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("217.77.12.236");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public Queue auditoriaQueue() {
        return new Queue("auditoria_queue", true);
    }

    @Bean
    public Queue notificacionesQueue() {
        return new Queue("notificaciones_queue", true);
    }

    @Bean
    public DirectExchange progresoExchange() {
        return new DirectExchange("progreso_exchange");
    }

    @Bean
    public Binding bindingAuditoria(Queue auditoriaQueue, DirectExchange progresoExchange) {
        return BindingBuilder.bind(auditoriaQueue).to(progresoExchange).with("progreso.actualizado");
    }

    @Bean
    public Binding bindingNotificaciones(Queue notificacionesQueue, DirectExchange progresoExchange) {
        return BindingBuilder.bind(notificacionesQueue).to(progresoExchange).with("progreso.actualizado");
    }
}
