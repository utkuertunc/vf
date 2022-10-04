package com.utkuertunc.vf.validation;

import com.utkuertunc.vf.annotation.VehicleTypeValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
    Custom validator for annotation. Vehicle type input must be case sensitive Car, Jeep or Truck
 */
public class VehicleTypeValidator implements ConstraintValidator<VehicleTypeValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("Car") || value.equals("Jeep") || value.equals("Truck");
    }
}
