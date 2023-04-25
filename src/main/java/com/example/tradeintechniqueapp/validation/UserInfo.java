package com.example.tradeintechniqueapp.validation;

import jakarta.validation.Payload;

public @interface UserInfo {
    String message() default "{jakarta.validation.constraints.NotBlank.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
