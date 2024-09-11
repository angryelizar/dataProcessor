package kg.angryelizar.batchsplitter.service.impl;

import kg.angryelizar.batchsplitter.service.MainService;
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
public class MainServiceImpl implements MainService {
    @Value("${file.storage.path}")
    private String DATA_DIR;

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
            // логика по созданию первого файла и вписывания в него джейсона
            return;
        }

        /// Если файлы есть - ищем файлы за такую же дату

        List<File> filesByStrDate = getFilesByName(String.format("%s-%s", type, LocalDate.now()), files);
        if (filesByStrDate.isEmpty()) {
            /// Если файлов за эту дату нету - логика по созданию первого файла и вписывания в него джейсона
            log.error("No files found for type {}", type);
            return;
        }

        /// Если нашли файлы за эту дату - достаем самый свежий по индексу
        log.info("Found {} files", filesByStrDate.size());
        filesByStrDate.sort(Comparator.comparing(File::getName).reversed());
        log.info("The newest file {}", filesByStrDate.get(0));
        filesByStrDate.sort(Comparator.comparing(File::getName));
        log.info("The oldest file {}", filesByStrDate.get(0));


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
