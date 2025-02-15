package com.example.aepb;

import com.example.aepb.exception.ParkingLotFullException;
import com.example.aepb.exception.TicketInvalidException;
import com.example.aepb.pakingboy.GraduateParkingBoy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GraduateParkingBoyTest {
    @Test
    void should_success_get_the_first_parking_lot_ticket_when_park_car_give_all_parking_lot_not_full() {
        ParkingLot parkingLotA = new ParkingLot(100);
        ParkingLot parkingLotB = new ParkingLot(100);
        ParkingLot parkingLotC = new ParkingLot(100);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car givenCar = new Car("鄂A12345");

        Ticket ticket = graduateParkingBoy.park(givenCar);

        assertNotNull(ticket);
        Car pickedCar = parkingLotA.pick(ticket);
        assertEquals(givenCar, pickedCar);
    }


    @Test
    void should_success_park_in_B_parking_lot_when_park_car_give_A_parking_lot_is_full_and_B_C_parking_lot_not_full() {
        ParkingLot parkingLotA = new ParkingLot(1);
        parkingLotA.park(new Car("鄂B12345"));
        ParkingLot parkingLotB = new ParkingLot(100);
        ParkingLot parkingLotC = new ParkingLot(100);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car givenCar = new Car("鄂A12345");

        Ticket ticket = graduateParkingBoy.park(givenCar);

        assertNotNull(ticket);
        Car pickedCar = parkingLotB.pick(ticket);
        assertEquals(givenCar, pickedCar);
    }

    @Test
    void should_success_pick_the_car_from_B_parking_lot_when_pick_car_give_B_parking_lot_ticket() {
        ParkingLot parkingLotA = new ParkingLot(100);
        ParkingLot parkingLotB = new ParkingLot(100);
        Ticket ticket = parkingLotB.park(new Car("鄂A12345"));
        ParkingLot parkingLotC = new ParkingLot(100);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));

        Car car = graduateParkingBoy.pick(ticket);

        assertNotNull(car);
        assertEquals("鄂A12345", car.getLicenseNumber());
    }

    @Test
    void should_throw_exception_when_parking_car_give_A_B_C_parking_is_full() {
        ParkingLot parkingLotA = new ParkingLot(0);
        ParkingLot parkingLotB = new ParkingLot(0);
        ParkingLot parkingLotC = new ParkingLot(0);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car car = new Car("鄂A12345");

        ParkingLotFullException exception = assertThrows(ParkingLotFullException.class,
            () -> graduateParkingBoy.park(car));

        assertEquals("all parking lot is full", exception.getMessage());
    }

    @Test
    void should_throw_exception_when_parking_car_give_two_car_with_same_license_number() {
        ParkingLot parkingLotA = new ParkingLot(10);
        ParkingLot parkingLotB = new ParkingLot(10);
        parkingLotB.park(new Car("鄂A12345"));
        ParkingLot parkingLotC = new ParkingLot(10);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car car = new Car("鄂A12345");

        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> graduateParkingBoy.park(car));

        assertEquals("the car has checked in", exception.getMessage());
    }

    @Test
    void should_throw_exception_when_pick_car_give_car_had_been_pick() {
        ParkingLot parkingLotA = new ParkingLot(10);
        ParkingLot parkingLotB = new ParkingLot(10);
        ParkingLot parkingLotC = new ParkingLot(10);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));

        Car car = new Car("鄂A12345");
        Ticket ticket = graduateParkingBoy.park(car);
        graduateParkingBoy.pick(ticket);
        assertNotNull(car);
        assertEquals("鄂A12345", car.getLicenseNumber());


        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> graduateParkingBoy.pick(ticket));

        assertEquals("the ticket is invalid", exception.getMessage());
    }

    @Test
    void should_throw_exception_when_pick_car_give_ticket_invalid() {
        ParkingLot parkingLotA = new ParkingLot(10);
        ParkingLot parkingLotB = new ParkingLot(10);
        ParkingLot parkingLotC = new ParkingLot(10);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Ticket ticket = new Ticket();

        TicketInvalidException exception = assertThrows(TicketInvalidException.class,
            () -> graduateParkingBoy.pick(ticket));

        assertEquals("the ticket is invalid", exception.getMessage());
    }
}
