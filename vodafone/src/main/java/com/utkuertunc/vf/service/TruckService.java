package com.utkuertunc.vf.service;

import com.utkuertunc.vf.dto.ParkRequestDTO;
import com.utkuertunc.vf.model.base.Vehicle;

import java.util.List;

public interface TruckService {

    void parkTruck(int[] garage, List<Vehicle> allVehicles, ParkRequestDTO parkRequestDTO);

    boolean checkVehicleTypeForTruck(ParkRequestDTO parkRequestDTO);

    void leaveTruck(int[] garage, List<Vehicle> allVehicles, int id);

}
