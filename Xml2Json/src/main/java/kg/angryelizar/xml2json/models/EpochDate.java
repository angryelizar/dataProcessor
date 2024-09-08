package kg.angryelizar.xml2json.models;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class EpochDate {
    private Long epoch;
    private OffsetDateTime date;
}
