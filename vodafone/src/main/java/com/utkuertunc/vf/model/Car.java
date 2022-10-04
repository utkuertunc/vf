package com.utkuertunc.vf.model;

import com.utkuertunc.vf.constants.SizeConstants;
import com.utkuertunc.vf.constants.VehicleTypeConstants;
import com.utkuertunc.vf.model.base.Vehicle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car extends Vehicle {

    private String vehicleType= VehicleTypeConstants.CAR_TYPE;

    private int[] parkedSlots = new int[SizeConstants.CAR_MAX_SIZE];
}
