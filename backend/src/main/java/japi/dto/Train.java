package japi;

import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class Train {
    @JsonProperty("train_name")
    String name;
    List<String> stations;
}
