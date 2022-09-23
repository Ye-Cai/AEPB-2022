package com.example.aepb.exception;

public class TicketInvalidException extends RuntimeException{
    public TicketInvalidException() {
        super("the ticket is invalid");
    }
}
