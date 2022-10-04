package com.utkuertunc.vf.model.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

/*
    Base class for each vehicle type
*/
@Getter
@Setter
@Component
public abstract class Vehicle {

    private String vehiclePlate;

    private String vehicleColour;

    private Boolean isParking = true;
}
