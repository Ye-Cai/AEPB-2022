package com.example.aepb;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Car {
    private final String licenseNumber;

    public Car(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
