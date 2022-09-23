package com.example.aepb;

public class Ticket {
    private final ParkingLot parkingLot;

    public Ticket(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getParkingLotName() {
        return parkingLot.getName();
    }
}
