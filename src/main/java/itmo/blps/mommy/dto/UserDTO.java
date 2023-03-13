package itmo.blps.mommy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class UserDTO {

    @NonNull
    @JsonProperty(value = "email", required = true)
    private String email;


    @JsonProperty(value = "password", required = true)
    private String password;
}
