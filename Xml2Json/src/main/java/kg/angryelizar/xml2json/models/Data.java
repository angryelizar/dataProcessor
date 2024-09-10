package kg.angryelizar.xml2json.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Data {
    @JsonProperty("Method")
    private Method method;
    @JsonProperty("Process")
    private Process process;
    @JsonProperty("Layer")
    private String layer;
    @JsonProperty("Creation")
    private EpochDate creation;
    @JsonProperty("Type")
    private String type;
}
