package kg.angryelizar.batchsplitter.config;

import kg.angryelizar.batchsplitter.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


@Configuration
@Slf4j
@RequiredArgsConstructor
public class RabbitMqConfig {
    @Value("${key.rabbit.name}")
    private String KEY_RABBIT_NAME;
    private final MainService mainService;

    @Bean
    public Queue queue() {
        return new Queue(KEY_RABBIT_NAME, false);
    }

    @RabbitListener(queues = "${key.rabbit.name}")
    public void receive(String message) throws IOException {
        mainService.processMessage(message);
    }
}
