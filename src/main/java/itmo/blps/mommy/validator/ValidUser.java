package itmo.blps.mommy.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserValidator.class)
public @interface ValidUser {
    String message() default "Пользовать должен существовать в базе данных";

    String[] properties() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
