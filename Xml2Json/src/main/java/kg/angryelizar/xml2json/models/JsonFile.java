package kg.angryelizar.xml2json.models;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JsonFile {
    private Long count;
    private List<Data> data;
}
