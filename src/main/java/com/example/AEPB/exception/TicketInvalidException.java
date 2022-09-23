package com.example.AEPB.exception;

public class TicketInvalidException extends RuntimeException{
    public TicketInvalidException() {
        super("the ticket is invalid");
    }
}
