// File: com/src/util/PasswordValidator.java
package com.src.util;

@FunctionalInterface
public interface PasswordValidator {
    boolean validate(String password, String confirmPassword);
}
