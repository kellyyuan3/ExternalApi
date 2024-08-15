package com.example.WorldpayApi.Controller

import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import org.springframework.web.reactive.function.BodyInserters
import com.example.WorldpayApi.Model.*
import org.springframework.http.HttpStatusCode


@RestController
class WorldpayApiController(private val webClient: WebClient,
    private val webClientExt: WebClient) {

    //localhost api methods
    @RequestMapping("/hello")
    fun hello(): String {
        return "hello world"
    }

    @GetMapping("/callclienthello")
    fun getHelloClient(): Mono<String> {
        return webClient
            .get()
            .uri("/hello")
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
            .onStatus(HttpStatusCode::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
            .bodyToMono(String::class.java)
    }

    //external api methods
    @GetMapping("/ext")
    fun getExt(): Mono<Array<Any>> {
        return webClientExt
            .get()
            .uri("/posts")
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
            .onStatus(HttpStatusCode::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
            .bodyToMono(Array<Any>::class.java)
    }

    @GetMapping("/ext/{id}")
    fun getExt(@PathVariable id: Long): Mono<Object> {
        return webClientExt
            .get()
            .uri("/posts/${id}")
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
            .onStatus(HttpStatusCode::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
            .bodyToMono(Object::class.java)
    }

    @GetMapping("/ext/{id}/comments")
    fun getExtComments(@PathVariable id: Long): Mono<Array<Any>> {
        return webClientExt
            .get()
            .uri("/posts/${id}/comments")
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
            .onStatus(HttpStatusCode::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
            .bodyToMono(Array<Any>::class.java)
    }

    @PostMapping("/ext")
    fun postExt(): Mono<Object> {
        return webClientExt
            .post()
            .uri("/posts").body(BodyInserters.fromValue(Object(10, null, "How to Train your Dragon", "Step by step, how to train your dragon to ride!")))
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
            .onStatus(HttpStatusCode::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
            .bodyToMono(Object::class.java)
    }

    @PutMapping("/ext/{id}")
    fun putExt(@PathVariable id: Long): Mono<Object> {
        return webClientExt
            .put()
            .uri("/posts/${id}").body(BodyInserters.fromValue(Object(1, id, "How to Become a Millionaire", "5 easy steps!")))
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
            .onStatus(HttpStatusCode::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
            .bodyToMono(Object::class.java)
    }

    @PatchMapping("/ext/{id}")
    fun patchExt(@PathVariable id: Long): Mono<Object> {
        return webClientExt
            .patch()
            .uri("/posts/${id}").body(BodyInserters.fromValue(ObjectUpdate("foo")))
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
            .onStatus(HttpStatusCode::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
            .bodyToMono(Object::class.java)
    }

    @DeleteMapping("/ext/{id}")
    fun deleteExt(@PathVariable id: Long) : String {
        webClientExt
            .delete()
            .uri("/posts/${id}")
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
            .onStatus(HttpStatusCode::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
            .bodyToMono(Void::class.java)
        return "Resource Deleted"
    }
}