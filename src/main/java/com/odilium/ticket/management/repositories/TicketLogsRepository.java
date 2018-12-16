package com.odilium.ticket.management.repositories;

import com.odilium.ticket.management.mongo.entities.TicketLogs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketLogsRepository extends MongoRepository<TicketLogs, String> {
}
