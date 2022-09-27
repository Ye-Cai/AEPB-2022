package com.example.aepb;

import com.example.aepb.exception.CarHasCheckedInException;
import com.example.aepb.exception.ParkingLotFullException;
import com.example.aepb.exception.TicketInvalidException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int maxCapacity;
    private final Map<Ticket, Car> ticketCarMap;
    @Getter
    private final String name;

    public ParkingLot(int maxCapacity, String name) {
        this.maxCapacity = maxCapacity;
        this.ticketCarMap = new HashMap<>(maxCapacity);
        this.name = name;
    }

    public Ticket park(Car car) {
        if (ticketCarMap.size() >= maxCapacity) {
            throw ParkingLotFullException.ofOne();
        }
        checkCarExistInParkingLot(car);
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public void checkCarExistInParkingLot(Car car) {
        if (ticketCarMap.containsValue(car)) {
            throw new CarHasCheckedInException();
        }
    }

    public Car pick(Ticket ticket) {
        if (!ticketCarMap.containsKey(ticket)) {
            throw new TicketInvalidException();
        }
        Car car = ticketCarMap.get(ticket);
        ticketCarMap.remove(ticket);
        return car;
    }

    public boolean isContainTicket(Ticket ticket) {
        return ticketCarMap.containsKey(ticket);
    }

    public boolean isFull() {
        return ticketCarMap.size() == maxCapacity;
    }

    public int getEmptySize() {
        return maxCapacity - ticketCarMap.size();
    }
}
