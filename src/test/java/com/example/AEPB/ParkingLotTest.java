package com.example.AEPB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    @Test
    void should_get_ticket_when_park_a_car() {
        Car car = new Car("鄂A123456");

        ParkingLot parkingLot = new ParkingLot();

        Ticket ticket = parkingLot.park(car);
        assertNotNull(ticket);
    }
}