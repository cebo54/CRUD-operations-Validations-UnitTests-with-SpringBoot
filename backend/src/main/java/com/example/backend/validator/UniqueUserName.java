package com.example.backend.validator;

import javax.validation.Constraint;

@Constraint(validatedBy = {UniqueUserNameValidator.class})
public @interface UniqueUserName {
    java.lang.String message() default "{javax.validation.constraints.UniqueUserName.message}";

    java.lang.Class<?>[] groups() default {};

    java.lang.Class<? extends javax.validation.Payload>[] payload() default {};

}
