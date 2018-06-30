package com.nepxion.discovery.plugin.example;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.nepxion.discovery.plugin.example.extension.MyDiscoveryListener;
import com.nepxion.discovery.plugin.example.extension.MyRegisterListener;
import com.nepxion.discovery.plugin.example.extension.MySubscriber;
import com.nepxion.discovery.plugin.example.impl.DiscoveryConfigAdapter;

@SpringBootApplication
@EnableDiscoveryClient
public class DiscoveryApplicationA1 {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "a1");

        new SpringApplicationBuilder(DiscoveryApplicationA1.class).web(true).run(args);
    }

    @Bean
    public DiscoveryConfigAdapter discoveryConfigAdapter() {
        return new DiscoveryConfigAdapter();
    }

    @Bean
    public MyRegisterListener myRegisterListener() {
        return new MyRegisterListener();
    }

    @Bean
    public MyDiscoveryListener myDiscoveryListener() {
        return new MyDiscoveryListener();
    }

    @Bean
    public MySubscriber mySubscriber() {
        return new MySubscriber();
    }
}