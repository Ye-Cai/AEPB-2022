package com.example.AEPB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    @Test
    void should_success_get_ticket_when_park_a_car() {
        ParkingLot parkingLot = new ParkingLot(100);
        Car car = new Car("鄂A123456");

        Ticket ticket = parkingLot.park(car);

        assertNotNull(ticket);
    }

    @Test
    void should_success_get_car_when_use_a_correct_ticket() {
        ParkingLot parkingLot = new ParkingLot(100);
        Car car = new Car("鄂A123456");
        Ticket ticket = parkingLot.park(car);

        Car pickedCar = parkingLot.pick(ticket);

        assertEquals(car, pickedCar);
    }

    @Test
    void should_error_for_the_parking_lot_is_full_when_park_a_car() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("鄂A123456");
        parkingLot.park(car);
        Car car2 = new Car("鄂A654321");

        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> parkingLot.park(car2));

        assertEquals("the parking lot is full", exception.getMessage());
    }

    @Test
    void should_error_for_the_car_has_checked_in_when_park_a_car() {
        ParkingLot parkingLot = new ParkingLot(100);
        Car car = new Car("鄂A123456");
        parkingLot.park(car);
        Car car2 = new Car("鄂A123456");

        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> parkingLot.park(car2));

        assertEquals("the car has checked in", exception.getMessage());
    }
}