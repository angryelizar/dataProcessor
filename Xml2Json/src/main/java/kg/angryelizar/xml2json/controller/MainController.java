package kg.angryelizar.xml2json.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.angryelizar.xml2json.models.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "The main controller")
public class MainController {
    @Operation(summary = "The main endpoint - load XML here!")
    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String loadXML(@RequestBody Data data) {
        return data.toString();
    }
}
