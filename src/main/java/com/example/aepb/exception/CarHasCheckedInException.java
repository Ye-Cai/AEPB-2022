package com.example.aepb.exception;

public class CarHasCheckedInException extends RuntimeException{
    public CarHasCheckedInException() {
        super("the car has checked in");
    }
}
