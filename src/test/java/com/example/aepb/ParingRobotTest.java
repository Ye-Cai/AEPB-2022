package com.example.aepb;

import com.example.aepb.exception.ParkingLotFullException;
import com.example.aepb.exception.TicketInvalidException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParingRobotTest {
    @Test
    void should_success_get_the_empty_count_max_parking_lot_ticket_when_park_car_by_robot_give_all_parking_lot_same_empty_rate() {
        ParkingLot parkingLotA = new ParkingLot(100);
        ParkingLot parkingLotB = new ParkingLot(100);
        ParkingLot parkingLotC = new ParkingLot(100);
        ParkingRobot parkingRobot = new ParkingRobot(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car givenCar = new Car("鄂A12345");

        Ticket ticket = parkingRobot.park(givenCar);

        assertNotNull(ticket);
        Car pickedCar = parkingLotA.pick(ticket);
        assertEquals(givenCar, pickedCar);
    }

}
