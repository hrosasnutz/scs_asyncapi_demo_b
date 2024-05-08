package io.codegitters.scs_asyncapi_demo_b;

import io.codegitters.messages.notification.NotificationDataMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ErrorMessage;

import java.util.function.Consumer;

@Slf4j
@SpringBootApplication
public class ScsAsyncapiDemoBApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScsAsyncapiDemoBApplication.class, args);
	}

	@Bean
	public Consumer<Message<NotificationDataMessage>> notificationMessageSub() {
		return message -> log.info("Received notification message: {}", message);
	}

	@Bean
	public Consumer<ErrorMessage> notificationMessageSubErrorHandler() {
		return error -> {
			log.error("Error message: {}", error);
			var content = new String((byte[]) error.getOriginalMessage().getPayload());
			log.error("Error content: {}", content);
		};
	}
}
