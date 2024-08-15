package com.example.WorldpayApi.Configuration

import org.springframework.context.annotation.*
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun webClient(): WebClient = WebClient.create("http://localhost:8080")

    @Bean
    fun webClientExt(): WebClient = WebClient.create("https://jsonplaceholder.typicode.com") //Worldpay Api URL would go here
}