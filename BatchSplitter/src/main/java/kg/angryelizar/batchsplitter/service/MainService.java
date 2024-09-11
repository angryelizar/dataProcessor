package kg.angryelizar.batchsplitter.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface MainService {
    void processMessage(String message) throws IOException;
}
