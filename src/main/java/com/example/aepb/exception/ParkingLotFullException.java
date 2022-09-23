package com.example.aepb.exception;

public class ParkingLotFullException extends RuntimeException{
    private ParkingLotFullException(String message) {
        super(message);
    }

    public static ParkingLotFullException ofOne(){
        return new ParkingLotFullException("the parking lot is full");
    }

    public static ParkingLotFullException ofAll(){
        return new ParkingLotFullException("all parking lot is full");
    }
}
