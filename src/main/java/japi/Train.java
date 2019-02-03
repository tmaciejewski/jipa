package japi;

import lombok.Value;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

@Value
public class Train {
    @JsonProperty("train_name")
    String name;
    List<String> stations;
}
