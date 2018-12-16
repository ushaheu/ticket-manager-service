package com.odilium.ticket.management.repositories;

import com.odilium.ticket.management.cache.entities.TicketInformation;
import org.springframework.data.repository.CrudRepository;

public interface TicketInformationRepository extends CrudRepository<TicketInformation, String> {
}
