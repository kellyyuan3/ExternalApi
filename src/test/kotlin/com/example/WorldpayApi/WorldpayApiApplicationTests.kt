package com.example.WorldpayApi

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.mockito.Mockito.*
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.*
import reactor.core.publisher.Mono
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import com.example.WorldpayApi.Controller.WorldpayApiController

@SpringBootTest
class WorldpayApiApplicationTests {

	private val webClient = mock(WebClient::class.java)
	private val webClientExt = mock(WebClient::class.java)
	private val controller = WorldpayApiController(webClient, webClientExt)

	@Test
	fun `test deletePost`() {
		val response = Mono.just("Resource Deleted")
		`when`(webClientExt.delete().uri("/posts/1").retrieve().bodyToMono(Void::class.java).thenReturn(response))
		val result = controller.deleteExt(1)
		assert(result == "Resource Deleted")
	}
}
