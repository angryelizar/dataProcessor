package kg.angryelizar.xml2json.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.Data;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Process {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Start")
    private EpochDate start;
}
