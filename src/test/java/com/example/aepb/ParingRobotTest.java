package com.example.aepb;

import com.example.aepb.exception.ParkingLotFullException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void should_success_get_the_empty_rate_max_parking_lot_B_ticket_when_park_car_given_parking_empty_rate_parking_lot_B_and_C_max() {
        ParkingLot parkingLotA = new ParkingLot(2);
        parkingLotA.park(new Car("鄂A11111"));
        ParkingLot parkingLotB = new ParkingLot(3);
        parkingLotB.park(new Car("鄂A22222"));
        ParkingLot parkingLotC = new ParkingLot(3);
        parkingLotC.park(new Car("鄂A33333"));
        ParkingBoy parkingRobot = new ParkingRobot(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car givenCar = new Car("鄂A12345");

        Ticket ticket = parkingRobot.park(givenCar);

        assertNotNull(ticket);
        Car pickedCar = parkingLotB.pick(ticket);
        assertEquals(givenCar, pickedCar);
    }

    @Test
    void should_throw_exception_when_parking_car_give_A_B_C_parking_is_full() {
        ParkingLot parkingLotA = new ParkingLot(0);
        ParkingLot parkingLotB = new ParkingLot(0);
        ParkingLot parkingLotC = new ParkingLot(0);
        ParkingRobot parkingRobot = new ParkingRobot(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car car = new Car("鄂A12345");

        ParkingLotFullException exception = assertThrows(ParkingLotFullException.class,
            () -> parkingRobot.park(car));

        assertEquals("all parking lot is full", exception.getMessage());
    }

    @Test
    void should_throw_exception_when_parking_car_give_two_car_with_same_license_number() {
        ParkingLot parkingLotA = new ParkingLot(10);
        ParkingLot parkingLotB = new ParkingLot(10);
        ParkingLot parkingLotC = new ParkingLot(10);
        ParkingRobot parkingRobot = new ParkingRobot(List.of(parkingLotA, parkingLotB, parkingLotC));
        Car givenCar = new Car("鄂A12345");
        parkingLotB.park(givenCar);

        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> parkingRobot.park(givenCar));

        assertEquals("the car has checked in", exception.getMessage());
    }

}
