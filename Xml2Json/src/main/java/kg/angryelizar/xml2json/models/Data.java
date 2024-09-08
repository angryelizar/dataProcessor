package kg.angryelizar.xml2json.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Data {
    private Method method;
    private Process process;
    private String layer;
    private EpochDate creation;
    private String type;
}
