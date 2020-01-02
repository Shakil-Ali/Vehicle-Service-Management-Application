package com.example.vehicleservicemanagementapplication;

public class Vehicles
{
    String vehicleId;
    String vehicleRegistration;
    String vehicleMake;

    // Base constructor - created for when values are read
    public Vehicles()
    {

    }

    // Constructor for initialised variables
    public Vehicles(String vehicleId, String vehicleRegistration, String vehicleMake)
    {
        this.vehicleId = vehicleId;
        this.vehicleRegistration = vehicleRegistration;
        this.vehicleMake = vehicleMake;
    }


    // Getters required when reading the values
    public String getVehicleId()
    {
        return vehicleId;
    }

    public String getVehicleRegistration()
    {
        return vehicleRegistration;
    }

    public String getVehicleMake()
    {
        return vehicleMake;
    }


    // end of class
}
