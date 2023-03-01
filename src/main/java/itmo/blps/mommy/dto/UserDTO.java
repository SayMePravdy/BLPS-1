package itmo.blps.mommy.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {
    @NonNull
    @Email(message = "Невалидный mail")
    @NotEmpty(message = "Mail не может быть пустым!")
    private String email;

    @NonNull
    @NotEmpty(message = "Password не должен быть пустым!")
    private String password;
}
