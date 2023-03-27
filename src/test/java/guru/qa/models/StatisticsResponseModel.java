package guru.qa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class StatisticsResponseModel {
    @JsonProperty("contacts")
    private SingleUserData contacts;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public @Data
    static class SingleUserData {
        private Integer total;
    }

}
