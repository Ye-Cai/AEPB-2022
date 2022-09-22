package com.example.AEPB;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int maxCapacity;
    private final Map<Ticket, Car> ticketCarMap;

    public ParkingLot(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.ticketCarMap = new HashMap<>(maxCapacity);
    }

    public Ticket park(Car car) {
        if (ticketCarMap.size() >= maxCapacity) {
            throw new RuntimeException("the parking lot is full");
        }
        if(ticketCarMap.containsValue(car)){
            throw new RuntimeException("the car has checked in");
        }
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car pick(Ticket ticket) {
        if(!ticketCarMap.containsKey(ticket)){
            throw new RuntimeException("the ticket is invalid");
        }
        Car car = ticketCarMap.get(ticket);
        ticketCarMap.remove(ticket);
        return car;
    }
}
