package com.example.AEPB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    @Test
    void should_get_ticket_when_park_a_car() {
        ParkingLot parkingLot = new ParkingLot(100);
        Car car = new Car("鄂A123456");

        Ticket ticket = parkingLot.park(car);

        assertNotNull(ticket);
    }

    @Test
    void should_get_car_when_use_a_correct_ticket() {
        ParkingLot parkingLot = new ParkingLot(100);
        Car car = new Car("鄂A123456");
        Ticket ticket = parkingLot.park(car);

        Car pickedCar = parkingLot.pick(ticket);

        assertEquals(car, pickedCar);
    }
}