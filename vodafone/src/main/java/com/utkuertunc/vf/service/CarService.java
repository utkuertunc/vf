package com.utkuertunc.vf.service;

import com.utkuertunc.vf.dto.ParkRequestDTO;
import com.utkuertunc.vf.model.base.Vehicle;

import java.util.List;

public interface CarService {

    void parkCar(int[] garage, List<Vehicle> allVehicles, ParkRequestDTO parkRequestDTO);

    boolean checkVehicleTypeForCar(ParkRequestDTO parkRequestDTO);

    void leaveCar(int[] garage, List<Vehicle> allVehicles, int id);
}
