package com.utkuertunc.vf.annotation;

import com.utkuertunc.vf.validation.VehicleTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target( { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = VehicleTypeValidator.class)
public @interface VehicleTypeValidation {

    String message() default "Invalid vehicle type: Vehicle type must be case sensitive Car, Jeep, Truck";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
