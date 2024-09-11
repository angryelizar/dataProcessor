package kg.angryelizar.batchsplitter;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class BatchSplitterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchSplitterApplication.class, args);
    }

}
