package guru.qa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class SingleUserResponseModel {
    @JsonProperty("data")
    private SingleUserData user;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public @Data
    static class SingleUserData {

        private Integer id;
        private String email;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;


    }
}
