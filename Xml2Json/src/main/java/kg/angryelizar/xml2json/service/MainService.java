package kg.angryelizar.xml2json.service;

import kg.angryelizar.xml2json.models.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface MainService {
    HttpStatus saveFile(Data data) throws IOException;
}
