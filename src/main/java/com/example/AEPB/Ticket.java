package com.example.AEPB;

public class Ticket {
    private ParkingLot parkingLot;

    public String getParkingLotName() {
        return parkingLot.getName();
    }

    public Ticket(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
    public Ticket() {
    }
}
