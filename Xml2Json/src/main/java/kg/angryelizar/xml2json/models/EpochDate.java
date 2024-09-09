package kg.angryelizar.xml2json.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class EpochDate {
    @JsonProperty("Epoch")
    private Long epoch;
    @JsonProperty("Date")
    private String date;
}
