package kg.angryelizar.xml2json.service.impl;

import ch.qos.logback.core.net.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import kg.angryelizar.xml2json.models.Data;
import kg.angryelizar.xml2json.models.JsonFile;
import kg.angryelizar.xml2json.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {
    private final RabbitTemplate rabbitTemplate;
    @Value("${file.storage.path}")
    private String DATA_DIR;

    @Override
    public HttpStatus saveFile(Data data) throws IOException {
        if (data.getType() == null) {
            log.error("Empty type!");
            return HttpStatus.BAD_REQUEST;
        }
        makeFile(getFileName(data.getType()), data);
        return HttpStatus.CREATED;
    }

    @SneakyThrows
    private void makeFile(String fileName, Data data) throws IOException {
        Path directoryPath = Paths.get(DATA_DIR);
        Path filePath = directoryPath.resolve(fileName);

        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        if (Files.exists(filePath)) {
            String rawJson = new String(Files.readAllBytes(filePath));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFile file = objectMapper.readValue(rawJson, JsonFile.class);
            file.getData().add(data);
            file.setCount((long) file.getData().size());
            rabbitTemplate.convertAndSend("myQueue", saveJson(filePath, file, fileName));
        } else {
            JsonFile file = makeJsonDataFile(data);
            Files.createFile(filePath);
            rabbitTemplate.convertAndSend("myQueue", saveJson(filePath, file, fileName));
        }
    }

    private String saveJson(Path filePath, JsonFile file, String fileName) throws IOException {
        String fileContent = getJsonString(file);
        Files.write(filePath, fileContent.getBytes());
        log.info("{} has been saved to {}", fileName, filePath);
        return fileContent;
    }

    private JsonFile makeJsonDataFile(Data data) {
        return new JsonFile(1L, List.of(data));
    }

    private String getFileName(String type) {
        return String.format("%s-%s.log", type.trim(), LocalDate.now());
    }

    @SneakyThrows
    private String getJsonString(JsonFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(file);
    }
}
