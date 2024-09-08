package kg.angryelizar.xml2json.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Method {
    private String name;
    private String type;
    private String assembly;
}
