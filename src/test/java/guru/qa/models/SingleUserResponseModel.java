package guru.qa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class SingleUserResponseModel {

        private Integer id;
        private String email;
        private String nickname;
        private String realNickname;

}
