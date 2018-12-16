package com.odilium.ticket.management.cache.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("ticket-information")
public class TicketInformation {

    @Id
    private String ticketId;
    private String dateOfEscalation;

    public TicketInformation() {
    }

    public TicketInformation(String ticketId, String dateOfEscalation) {
        this.ticketId = ticketId;
        this.dateOfEscalation = dateOfEscalation;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getDateOfEscalation() {
        return dateOfEscalation;
    }

    public void setDateOfEscalation(String dateOfEscalation) {
        this.dateOfEscalation = dateOfEscalation;
    }

    @Override
    public String toString() {
        return "TicketInformation{" +
                "ticketId='" + ticketId + '\'' +
                ", dateOfEscalation='" + dateOfEscalation + '\'' +
                '}';
    }
}
