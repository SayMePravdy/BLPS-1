package itmo.blps.mommy.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserDTO {
    @NonNull
    private String email;

    @NonNull
    private String password;


}
