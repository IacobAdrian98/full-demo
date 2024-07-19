package com.example.service;

import com.example.model.Car;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class DefaultCarService implements CarService {
    public List<Car> getAllCars() {
        return Car.listAll();
    }

    @Override
    public Car getCarByModelAndYear(String vehicleModel, int vehicleYear) {
        return Car.find("model = ?1 and yearOfFabrication=?2", vehicleModel, vehicleYear)
                .firstResult();
    }

    @Override
    public Car addNewCar(Car car) {
        Car.persist(car);
        return car;
    }
}
