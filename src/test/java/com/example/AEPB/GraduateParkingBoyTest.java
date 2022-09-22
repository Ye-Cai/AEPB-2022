package com.example.AEPB;

import com.example.AEPB.exception.ParkingLotFullException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GraduateParkingBoyTest {
    @Test
    void should_success_get_the_first_parking_lot_ticket_when_park_car_give_all_parking_lot_not_full() {
        ParkingLot parkingLotA = new ParkingLot(100, "A");
        ParkingLot parkingLotB = new ParkingLot(100, "B");
        ParkingLot parkingLotC = new ParkingLot(100, "C");
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car car = new Car("鄂A12345");

        Ticket ticket = graduateParkingBoy.park(car);

        assertNotNull(ticket);
        assertEquals("A", ticket.getParkingLotName());
    }


    @Test
    void should_success_park_in_B_parking_lot_when_park_car_give_A_parking_lot_is_full_and_B_C_parking_lot_not_full() {
        ParkingLot parkingLotA = new ParkingLot(1, "A");
        parkingLotA.park(new Car("鄂B12345"));
        ParkingLot parkingLotB = new ParkingLot(100, "B");
        ParkingLot parkingLotC = new ParkingLot(100, "C");
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car car = new Car("鄂A12345");

        Ticket ticket = graduateParkingBoy.park(car);

        assertNotNull(ticket);
        assertEquals("B", ticket.getParkingLotName());
    }

    @Test
    void should_success_pick_the_car_from_B_parking_lot_when_pick_car_give_B_parking_lot_ticket() {
        ParkingLot parkingLotA = new ParkingLot(100, "A");
        ParkingLot parkingLotB = new ParkingLot(100, "B");
        Ticket ticket = parkingLotB.park(new Car("鄂A12345"));
        ParkingLot parkingLotC = new ParkingLot(100, "C");
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));

        Car car = graduateParkingBoy.pick(ticket);

        assertNotNull(car);
        assertEquals("鄂A12345", car.getLicenseNumber());
    }

    @Test
    void should_throw_exception_when_parking_car_give_A_B_C_parking_is_full() {
        ParkingLot parkingLotA = new ParkingLot(0, "A");
        ParkingLot parkingLotB = new ParkingLot(0, "B");
        ParkingLot parkingLotC = new ParkingLot(0, "C");
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car car = new Car("鄂A12345");

        ParkingLotFullException exception = assertThrows(ParkingLotFullException.class,
            () -> graduateParkingBoy.park(car));

        assertEquals("all parking lot is full", exception.getMessage());
    }
}
