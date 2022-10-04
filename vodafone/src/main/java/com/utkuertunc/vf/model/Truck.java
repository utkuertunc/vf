package com.utkuertunc.vf.model;

import com.utkuertunc.vf.constants.SizeConstants;
import com.utkuertunc.vf.constants.VehicleTypeConstants;
import com.utkuertunc.vf.model.base.Vehicle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Truck extends Vehicle {

    private String vehicleType= VehicleTypeConstants.TRUCK_TYPE;

    private int[] parkedSlots = new int[SizeConstants.TRUCK_MAX_SIZE];
}