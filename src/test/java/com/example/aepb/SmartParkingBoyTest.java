package com.example.aepb;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SmartParkingBoyTest {
    @Test
    void should_success_get_the_empty_count_max_parking_lot_ticket_when_park_car_give_all_parking_lot_same_empty_count() {
        ParkingLot parkingLotA = new ParkingLot(100, "A");
        ParkingLot parkingLotB = new ParkingLot(100, "B");
        ParkingLot parkingLotC = new ParkingLot(100, "C");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car car = new Car("鄂A12345");

        Ticket ticket = smartParkingBoy.park(car);

        assertNotNull(ticket);
        assertEquals("A", ticket.getParkingLotName());
    }

}