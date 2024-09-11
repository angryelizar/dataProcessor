package kg.angryelizar.batchsplitter.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kg.angryelizar.batchsplitter.models.Data;
import kg.angryelizar.batchsplitter.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    @Value("${file.storage.path}")
    private String DATA_DIR;
    private static final int MAX_ENTRIES_PER_FILE = 100;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void processMessage(String message) throws IOException {
        String[] messageParts = message.split("\\|");
        String type = messageParts[0];
        String Json = messageParts[1];

        Path directoryPath = Paths.get(DATA_DIR);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        File[] files = directoryPath.toFile().listFiles();

        if (files == null || files.length == 0) {
            List<Data> dataList = new ArrayList<>();
            dataList.add(objectMapper.readValue(Json, Data.class));
            saveJsonFile(type, dataList, 1, directoryPath);
            return;
        }

        List<File> filesByStrDate = getFilesByName(String.format("%s-%s", type, LocalDate.now()), files);
        if (filesByStrDate.isEmpty()) {
            List<Data> dataList = new ArrayList<>();
            dataList.add(objectMapper.readValue(Json, Data.class));
            saveJsonFile(type, dataList, 1, directoryPath);
            return;
        }
        log.info("Found {} files", filesByStrDate.size());
        File newestFile = getTheNewestFile(filesByStrDate);
        log.info("The newest file {}", newestFile);

        if (isMoreOrEquals(newestFile)) {
            List<Data> dataList = new ArrayList<>();
            dataList.add(objectMapper.readValue(Json, Data.class));
            saveJsonFile(type, dataList, filesByStrDate.size() + 1, directoryPath);
            return;
        }
        List<Data> dataList = readJsonFile(newestFile);
        dataList.add(objectMapper.readValue(Json, Data.class));
        saveJsonFile(type, dataList, filesByStrDate.size(), directoryPath);
    }

    private File saveJsonFile(String type, List<Data> data, Integer count, Path directoryPath) throws IOException {
        String fileName = getFileName(type, count);
        Path filePath = directoryPath.resolve(fileName);
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        String content = objectMapper.writeValueAsString(data);
        Files.write(filePath, content.getBytes());
        log.info("Saving file {}", fileName);
        return filePath.toFile();
    }

    private List<Data> readJsonFile(File jsonFile) throws IOException {
        return objectMapper.readValue(jsonFile,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Data.class));
    }

    private Boolean isMoreOrEquals(File newestFile) throws IOException {
        return readJsonFile(newestFile).size() >= MAX_ENTRIES_PER_FILE;
    }

    private File getTheNewestFile(List<File> filesByStrDate) {
        filesByStrDate.sort(Comparator.comparing(File::getName).reversed());
        return filesByStrDate.get(0);
    }

    private List<File> getFilesByName(String fileName, File[] files) {
        List<File> result = new ArrayList<>();
        for (File file : files) {
            String strippedFileName = file.getName().substring(0, file.getName().length() - 4);
            log.warn("current file {}, filename for search {}", strippedFileName, fileName);
            if (strippedFileName.contains(fileName)) {
                result.add(file);
                log.warn("found file {}", strippedFileName);
            }
        }
        return result;
    }

    private String getFileName(String type, Integer index) {
        return String.format("%s-%s-%04d.log", type, LocalDate.now(), index);
    }

}
