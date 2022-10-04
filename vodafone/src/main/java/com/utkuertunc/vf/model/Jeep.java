package com.utkuertunc.vf.model;

import com.utkuertunc.vf.constants.SizeConstants;
import com.utkuertunc.vf.constants.VehicleTypeConstants;
import com.utkuertunc.vf.model.base.Vehicle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Jeep extends Vehicle {

    private String vehicleType= VehicleTypeConstants.JEEP_TYPE;

    private int[] parkedSlots = new int[SizeConstants.JEEP_MAX_SIZE];
}
