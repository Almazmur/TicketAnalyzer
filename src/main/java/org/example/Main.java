package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root =    objectMapper.readTree(new File("tickets.json")).get("tickets");


        List<Ticket> tickets = new ArrayList<>();
        for (JsonNode node : root) {
            Ticket ticket = new Ticket(
                    node.get("origin").asText(),
                    node.get("destination").asText(),
                    node.get("departure_date").asText(),
                    node.get("departure_time").asText(),
                    node.get("arrival_date").asText(),
                    node.get("arrival_time").asText(),
                    node.get("carrier").asText(),
                    node.get("price").asInt()
            );
            tickets.add(ticket);
        }

        // Фильтрация билетов Владивосток-Тель-Авив
        List<Ticket> vvoToTlvTickets = tickets.stream()
                .filter(t -> t.getOrigin().equals("VVO") && t.getDestination().equals("TLV"))
                .collect(Collectors.toList());

        // Минимальное время полета для каждого авиаперевозчика
        Map<String, Long> minFlightTimeByCarrier = new HashMap<>();
        for (Ticket ticket : vvoToTlvTickets) {
            long flightDuration = ticket.getFlightDuration();
            minFlightTimeByCarrier.merge(ticket.getCarrier(), flightDuration, Math::min);
        }

        System.out.println("Минимальное время полета для каждого авиаперевозчика:");
        minFlightTimeByCarrier.forEach((carrier, duration) -> System.out.printf("%s: %d минут\n", carrier, duration));

        // Средняя цена и медиана
        List<Integer> prices = vvoToTlvTickets.stream()
                .map(Ticket::getPrice)
                .sorted()
                .collect(Collectors.toList());

        double averagePrice = prices.stream().mapToInt(Integer::intValue).average().orElse(0);
        double medianPrice;
        int size = prices.size();
        if (size % 2 == 0) {
            medianPrice = (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2.0;
        } else {
            medianPrice = prices.get(size / 2);
        }

        double difference = averagePrice - medianPrice;

        System.out.printf("Средняя цена: %.2f\n", averagePrice);
        System.out.printf("Медиана цены: %.2f\n", medianPrice);
        System.out.printf("Разница между средней ценой и медианой: %.2f\n", difference);
    }
}
