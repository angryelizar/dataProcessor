package kg.angryelizar.batchsplitter.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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
