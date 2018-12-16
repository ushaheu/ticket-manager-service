package com.odilium.ticket.management.repositories;

import com.odilium.ticket.management.mongo.entities.LoginEntries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.stream.Stream;

@Repository
public interface LoginEntryRepository  extends PagingAndSortingRepository<LoginEntries, String> {

    LoginEntries findByUserNameAndLoginDateAndAgentLocation(String userName, String loginDate, String agentLocation);

    Stream<LoginEntries> findByAgentLoginTimeIsBetween(Date startDate, Date endDate);

    Page<LoginEntries> findByAgentLoginTimeIsBetweenOrderByLoginDateAscAgentLocationAsc(Date startDate, Date endDate, Pageable pageable);

    Page<LoginEntries> findByUserNameLikeAndAgentLoginTimeIsBetweenOrderByLoginDateAscAgentLocationAsc(String userName, Date startDate, Date endDate, Pageable pageable);
}
