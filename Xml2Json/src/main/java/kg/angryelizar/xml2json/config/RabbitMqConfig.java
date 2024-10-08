package kg.angryelizar.xml2json.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class RabbitMqConfig {
    @Value("${key.rabbit.name}")
    private String KEY_RABBIT_NAME;
    @Bean
    public Queue queue() {
        return new Queue(KEY_RABBIT_NAME, false);
    }
}
