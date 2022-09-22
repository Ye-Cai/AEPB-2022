package com.example.AEPB;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    @Test
    void should_success_get_ticket_when_park_a_car_give_right_license_number() {
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
    void should_throw_the_parking_lot_is_full_exception_when_park_a_car_given_full_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("鄂A123456");
        parkingLot.park(car);
        Car car2 = new Car("鄂A654321");

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> parkingLot.park(car2));

        assertEquals("the parking lot is full", exception.getMessage());
    }

    @Test
    void should_throw_the_car_has_checked_in_exception_when_park_a_car_give_two_cars_with_same_license_number() {
        ParkingLot parkingLot = new ParkingLot(100);
        Car car = new Car("鄂A123456");
        parkingLot.park(car);
        Car car2 = new Car("鄂A123456");

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> parkingLot.park(car2));

        assertEquals("the car has checked in", exception.getMessage());
    }

    @Test
    void should_throw_the_invalid_ticket_exception_when_pick_a_car_given_ticket_has_used() {
        ParkingLot parkingLot = new ParkingLot(100);
        Car car = new Car("鄂A123456");
        Ticket ticket = parkingLot.park(car);
        parkingLot.pick(ticket);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> parkingLot.pick(ticket));

        assertEquals("the ticket is invalid", exception.getMessage());
    }

    @Test
    void should_throw_the_invalid_ticket_exception_when_pick_a_car_give_wrong_ticket() {
        ParkingLot parkingLot = new ParkingLot(100);
        Ticket ticket = new Ticket();

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> parkingLot.pick(ticket));

        assertEquals("the ticket is invalid", exception.getMessage());
    }


    @Test
    void should_get_ticket_when_parking_by_gay_when_parkABC_is_not_full_and_carplate_number_is_valid() {
        List<ChildParkingLot> childParkingLots = List.of(
                new ChildParkingLot(1, "A"),
                new ChildParkingLot(2, "B"),
                new ChildParkingLot(3, "C"));
        ParkingLot parkingLot = new ParkingLot(100, childParkingLots);
        Car car = new Car("鄂A123456");
        Ticket ticket = parkingLot.park(car);
        assertEquals(car, ticket.getCar());
        assertEquals("A", ticket.getChildParkingLot().getName());
    }


}
