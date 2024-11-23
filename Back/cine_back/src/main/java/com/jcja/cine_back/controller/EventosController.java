package com.jcja.cine_back.controller;

import com.jcja.cine_back.eventos.RabbitMQProducer;
import com.jcja.cine_back.eventos.StatusChangeEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventosController {

    private final RabbitMQProducer producer;

    public EventosController(RabbitMQProducer producer) {
        this.producer = producer;
    }


}


