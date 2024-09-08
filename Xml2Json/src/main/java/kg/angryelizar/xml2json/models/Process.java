package kg.angryelizar.xml2json.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Process {
    private String name;
    private Long id;
    private EpochDate start;
}
