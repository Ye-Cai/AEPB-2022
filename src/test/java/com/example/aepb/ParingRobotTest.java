package com.example.aepb;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParingRobotTest {
    @Test
    void should_success_get_the_empty_rat_max_parking_lot_ticket_when_park_car_by_robot_give_all_parking_lot_same_empty_rate() {
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

    @Test
    void should_success_get_the_empty_rat_max_parking_lot_C_ticket_when_park_car_given_parking_empty_rat_parking_lot_C_max() {
        ParkingLot parkingLotA = new ParkingLot(2);
        parkingLotA.park(new Car("鄂A11111"));
        ParkingLot parkingLotB = new ParkingLot(3);
        parkingLotB.park(new Car("鄂A22222"));
        ParkingLot parkingLotC = new ParkingLot(4);
        parkingLotC.park(new Car("鄂A33333"));
        ParkingBoy parkingRobot = new ParkingRobot(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car givenCar = new Car("鄂A12345");

        Ticket ticket = parkingRobot.park(givenCar);

        assertNotNull(ticket);
        Car pickedCar = parkingLotC.pick(ticket);
        assertEquals(givenCar, pickedCar);
    }

}
