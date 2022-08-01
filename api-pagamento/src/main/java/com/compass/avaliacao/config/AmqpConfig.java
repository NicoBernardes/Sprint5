package com.compass.avaliacao.config;

public interface AmqpConfig<T> {
    void consumidor(T t);

}
