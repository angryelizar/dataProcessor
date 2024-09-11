package kg.angryelizar.batchsplitter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class RabbitMqConfig {
    @Bean
    public Queue queue() {
        return new Queue("myQueue", false);
    }

    @RabbitListener(queues = "myQueue")
    public void receive(String message) {
        log.info("Received: {}", message);
    }
}
