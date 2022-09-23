package com.example.aepb;

import java.util.Objects;

public class Car {
    private final String licenseNumber;

    public Car(String licenseNumber) {
        //TODO: 车牌校验
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Car car = (Car) obj;
        return Objects.equals(licenseNumber, car.licenseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licenseNumber);
    }
}
