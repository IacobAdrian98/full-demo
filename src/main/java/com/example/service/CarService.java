package com.example.service;

import com.example.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car getCarByModelAndYear(String model, int year);
    Car addNewCar(Car car);
}
