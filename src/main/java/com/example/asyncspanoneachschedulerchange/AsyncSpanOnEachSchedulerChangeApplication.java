package com.example.asyncspanoneachschedulerchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
public class AsyncSpanOnEachSchedulerChangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncSpanOnEachSchedulerChangeApplication.class, args);
	}

}

@RestController
class Endpoint {

	@GetMapping
	Mono<String> test() {
		return Mono.just("ok")
				.subscribeOn(Schedulers.single())
				.publishOn(Schedulers.parallel())
				.publishOn(Schedulers.elastic())
				.publishOn(Schedulers.parallel());
	}
}