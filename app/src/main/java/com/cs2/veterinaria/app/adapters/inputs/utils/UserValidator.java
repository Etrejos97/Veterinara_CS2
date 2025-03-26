package com.cs2.veterinaria.app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public class UserValidator extends SimpleValidator{

    public static String userNameValidator(String value) throws Exception {
        return stringValidator(value, "nombre de usuario");
    }

    public static String passwordValidator(String value) throws Exception {
        return stringValidator(value, "contraseña");
    }

}
