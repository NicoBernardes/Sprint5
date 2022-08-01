package com.compass.avaliacao.amqp;

public interface AmqpProdutor<T> {
    void producer(T t);
}
