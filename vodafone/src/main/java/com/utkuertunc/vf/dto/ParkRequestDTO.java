package com.utkuertunc.vf.dto;

import com.utkuertunc.vf.annotation.VehicleTypeValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ParkRequestDTO {

    @Size(min = 9, max = 9, message = "Invalid plate: Plate size must be 10")
    private String vehiclePlate;

    private String vehicleColour;

    @VehicleTypeValidation
    private String vehicleType;
}
