package kg.angryelizar.xml2json.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.angryelizar.xml2json.models.Data;
import kg.angryelizar.xml2json.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Tag(name = "The main controller")
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;


    @Operation(summary = "The main endpoint - load XML here!")
    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HttpStatus loadXML(@RequestBody Data data) throws IOException {
        return mainService.saveFile(data);
    }
}
