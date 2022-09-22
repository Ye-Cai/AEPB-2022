package com.example.AEPB;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GraduateParkingBoyTest {
    @Test
    void should_success_get_the_first_parking_lot_ticket_when_park_car_give_all_parking_lot_not_full() {
        ParkingLot parkingLotA = new ParkingLot(100);
        ParkingLot parkingLotB = new ParkingLot(100);
        ParkingLot parkingLotC = new ParkingLot(100);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car car = new Car("鄂A12345");

        Ticket ticket = graduateParkingBoy.park(car);

        assertNotNull(ticket);
        assertEquals("A", ticket.getParkingLotName());
    }
}