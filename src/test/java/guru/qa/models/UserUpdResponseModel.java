package guru.qa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class UserUpdResponseModel {
    private String name, job;
    private Date updatedAt;
}
