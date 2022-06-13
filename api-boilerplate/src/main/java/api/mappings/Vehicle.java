package api.mappings;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("client")
    private Integer client;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("model")
    private String model;
    @JsonProperty("year")
    private Integer year;
    @JsonProperty("type")
    private String type;
    @JsonProperty("plate")
    private String plate;
    @JsonProperty("active")
    private Boolean active;

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", client=" + client +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", type='" + type + '\'' +
                ", plate='" + plate + '\'' +
                ", active=" + active +
                '}';
    }

    public Vehicle(Integer id) {
        this.id = id;
    }
}
