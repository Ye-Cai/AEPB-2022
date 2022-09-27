package com.example.aepb;

import com.example.aepb.exception.ParkingLotFullException;
import com.example.aepb.exception.TicketInvalidException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SmartParkingBoyTest {
    @Test
    void should_success_get_the_empty_count_max_parking_lot_ticket_when_park_car_give_all_parking_lot_same_empty_count() {
        ParkingLot parkingLotA = new ParkingLot(100, "A");
        ParkingLot parkingLotB = new ParkingLot(100, "B");
        ParkingLot parkingLotC = new ParkingLot(100, "C");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car givenCar = new Car("鄂A12345");

        Ticket ticket = smartParkingBoy.park(givenCar);

        assertNotNull(ticket);
        Car pickedCar = parkingLotA.pick(ticket);
        assertEquals(givenCar, pickedCar);
    }

    @Test
    void should_success_get_the_empty_count_max_parking_lot_C_ticket_when_park_car_given_parking_empty_count_A_3_B_4_C_5() {
        ParkingLot parkingLotA = new ParkingLot(3, "A");
        ParkingLot parkingLotB = new ParkingLot(4, "B");
        ParkingLot parkingLotC = new ParkingLot(5, "C");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car givenCar = new Car("鄂A12345");

        Ticket ticket = smartParkingBoy.park(givenCar);

        assertNotNull(ticket);
        Car pickedCar = parkingLotC.pick(ticket);
        assertEquals(givenCar, pickedCar);
    }

    @Test
    void should_success_get_the_empty_count_max_parking_lot_A_ticket_when_park_car_given_parking_empty_count_A_6_B_3_C_5() {
        ParkingLot parkingLotA = new ParkingLot(6, "A");
        ParkingLot parkingLotB = new ParkingLot(3, "B");
        ParkingLot parkingLotC = new ParkingLot(5, "C");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car givenCar = new Car("鄂A12345");

        Ticket ticket = smartParkingBoy.park(givenCar);

        assertNotNull(ticket);
        Car pickedCar = parkingLotA.pick(ticket);
        assertEquals(givenCar, pickedCar);
    }

    @Test
    void should_success_pick_the_car_from_B_parking_lot_when_pick_car_give_B_parking_lot_ticket() {
        ParkingLot parkingLotA = new ParkingLot(100, "A");
        ParkingLot parkingLotB = new ParkingLot(100, "B");
        ParkingLot parkingLotC = new ParkingLot(100, "C");
        Car givenCar = new Car("鄂A12345");
        Ticket ticket = parkingLotB.park(givenCar);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));

        Car pickedCar = smartParkingBoy.pick(ticket);

        assertNotNull(pickedCar);
        assertEquals(givenCar, pickedCar);
    }

    @Test
    void should_throw_exception_when_parking_car_give_A_B_C_parking_is_full() {
        ParkingLot parkingLotA = new ParkingLot(0, "A");
        ParkingLot parkingLotB = new ParkingLot(0, "B");
        ParkingLot parkingLotC = new ParkingLot(0, "C");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car car = new Car("鄂A12345");

        ParkingLotFullException exception = assertThrows(ParkingLotFullException.class,
            () -> smartParkingBoy.park(car));

        assertEquals("all parking lot is full", exception.getMessage());
    }

    @Test
    void should_throw_exception_when_parking_car_give_two_car_with_same_license_number() {
        ParkingLot parkingLotA = new ParkingLot(10, "A");
        ParkingLot parkingLotB = new ParkingLot(10, "B");
        ParkingLot parkingLotC = new ParkingLot(10, "C");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car givenCar = new Car("鄂A12345");
        parkingLotB.park(givenCar);

        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> smartParkingBoy.park(givenCar));

        assertEquals("the car has checked in", exception.getMessage());
    }

    @Test
    void should_throw_exception_when_pick_car_give_car_had_been_pick() {
        ParkingLot parkingLotA = new ParkingLot(10, "A");
        ParkingLot parkingLotB = new ParkingLot(10, "B");
        ParkingLot parkingLotC = new ParkingLot(10, "C");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car car = new Car("鄂A12345");
        Ticket ticket = parkingLotB.park(car);
        parkingLotB.pick(ticket);

        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> smartParkingBoy.pick(ticket));

        assertEquals("the ticket is invalid", exception.getMessage());
    }

    @Test
    void should_throw_exception_when_pick_car_give_ticket_invalid() {
        ParkingLot parkingLotA = new ParkingLot(10, "A");
        ParkingLot parkingLotB = new ParkingLot(10, "B");
        ParkingLot parkingLotC = new ParkingLot(10, "C");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Ticket ticket = new Ticket();

        TicketInvalidException exception = assertThrows(TicketInvalidException.class,
            () -> smartParkingBoy.pick(ticket));

        assertEquals("the ticket is invalid", exception.getMessage());
    }
}
