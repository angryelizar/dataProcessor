package kg.angryelizar.batchsplitter.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Process {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Start")
    private EpochDate start;
}
