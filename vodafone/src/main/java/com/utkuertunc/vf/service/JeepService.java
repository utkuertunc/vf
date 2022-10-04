package com.utkuertunc.vf.service;

import com.utkuertunc.vf.dto.ParkRequestDTO;
import com.utkuertunc.vf.model.base.Vehicle;

import java.util.HashSet;
import java.util.List;

public interface JeepService {

    void parkJeep(int[] garage, List<Vehicle> allVehicles, ParkRequestDTO parkRequestDTO);

    boolean checkVehicleTypeForJeep(ParkRequestDTO parkRequestDTO);

    void leaveJeep(int[] garage, List<Vehicle> allVehicles, int id);
}
