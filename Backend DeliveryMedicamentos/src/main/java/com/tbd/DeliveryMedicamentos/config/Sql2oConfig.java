package com.tbd.DeliveryMedicamentos.config;

import org.sql2o.Sql2o;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Sql2oConfig {

    @Bean
    public Sql2o sql2o() {
        return new Sql2o(
                "jdbc:postgresql://dpg-d09tc6h5pdvs73d78g7g-a.oregon-postgres.render.com:5432/delivery_medicamentos_v1?sslmode=require",
                "delivery_medicamentos_user",
                "RXevS6iRkS3WKVtUoUy1Qg21dhPlXXOJ"
        );
    }
}

