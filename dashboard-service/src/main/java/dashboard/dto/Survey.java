package dashboard.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Survey implements Serializable {
    @JsonProperty("name")
    @NotNull
    private String name;
    @JsonProperty("age")
    @NotNull
    private int age;
    @JsonProperty("city")
    @NotNull
    private String city;
    @JsonProperty("platform")
    @NotNull
    private String platform;
    @JsonProperty("rating")
    @NotNull
    private int rating;
}
