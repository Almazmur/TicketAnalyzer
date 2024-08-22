package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {

    private final String origin;
    private final String destination;
    private final LocalDateTime departureDateTime;
    private final LocalDateTime arrivalDateTime;
    private final String carrier;
    private final int price;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");

    public Ticket(String origin, String destination, String departureDate, String departureTime,
                  String arrivalDate, String arrivalTime, String carrier, int price) {
        this.origin = origin;
        this.destination = destination;
        this.departureDateTime = LocalDateTime.parse(departureDate + " " + departureTime, DATE_FORMAT);
        this.arrivalDateTime = LocalDateTime.parse(arrivalDate + " " + arrivalTime, DATE_FORMAT);
        this.carrier = carrier;
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getCarrier() {
        return carrier;
    }

    public int getPrice() {
        return price;
    }

    public long getFlightDuration() {
        return Duration.between(departureDateTime, arrivalDateTime).toMinutes();
    }
}

