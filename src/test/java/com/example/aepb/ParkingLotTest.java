package com.example.aepb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingLotTest {

    @Test
    void should_success_get_ticket_when_park_a_car_give_right_license_number() {
        ParkingLot parkingLot = new ParkingLot(100, "default");
        Car car = new Car("鄂A123456");

        Ticket ticket = parkingLot.park(car);

        assertNotNull(ticket);
    }

    @Test
    void should_success_get_car_when_use_a_correct_ticket() {
        ParkingLot parkingLot = new ParkingLot(100, "default");
        Car car = new Car("鄂A123456");
        Ticket ticket = parkingLot.park(car);

        Car pickedCar = parkingLot.pick(ticket);

        assertEquals(car, pickedCar);
    }

    @Test
    void should_throw_the_parking_lot_is_full_exception_when_park_a_car_given_full_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1, "default");
        Car car = new Car("鄂A123456");
        parkingLot.park(car);
        Car car2 = new Car("鄂A654321");

        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> parkingLot.park(car2));

        assertEquals("the parking lot is full", exception.getMessage());
    }

    @Test
    void should_throw_the_car_has_checked_in_exception_when_park_a_car_give_two_cars_with_same_license_number() {
        ParkingLot parkingLot = new ParkingLot(100, "default");
        Car car = new Car("鄂A123456");
        parkingLot.park(car);
        Car car2 = new Car("鄂A123456");

        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> parkingLot.park(car2));

        assertEquals("the car has checked in", exception.getMessage());
    }

    @Test
    void should_throw_the_invalid_ticket_exception_when_pick_a_car_given_ticket_has_used() {
        ParkingLot parkingLot = new ParkingLot(100, "default");
        Car car = new Car("鄂A123456");
        Ticket ticket = parkingLot.park(car);
        parkingLot.pick(ticket);

        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> parkingLot.pick(ticket));

        assertEquals("the ticket is invalid", exception.getMessage());
    }

    @Test
    void should_throw_the_invalid_ticket_exception_when_pick_a_car_give_wrong_ticket() {
        ParkingLot parkingLot = new ParkingLot(100, "default");
        Ticket ticket = new Ticket();

        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> parkingLot.pick(ticket));

        assertEquals("the ticket is invalid", exception.getMessage());
    }

}
