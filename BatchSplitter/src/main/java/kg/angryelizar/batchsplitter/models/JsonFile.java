package kg.angryelizar.batchsplitter.models;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class JsonFile {
    private List<Data> data;
}
