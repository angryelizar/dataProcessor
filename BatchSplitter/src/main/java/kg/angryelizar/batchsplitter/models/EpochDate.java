package kg.angryelizar.batchsplitter.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EpochDate {
    @JsonProperty("Epoch")
    private Long epoch;
    @JsonProperty("Date")
    private String date;
}
