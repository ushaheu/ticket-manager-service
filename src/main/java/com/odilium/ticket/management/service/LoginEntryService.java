package com.odilium.ticket.management.service;

import com.odilium.ticket.management.helper.TicketManagerHelper;
import com.odilium.ticket.management.mongo.entities.LoginEntries;
import com.odilium.ticket.management.repositories.LoginEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LoginEntryService {

    private LoginEntryRepository loginEntryRepository;

    @Autowired
    public LoginEntryService(LoginEntryRepository loginEntryRepository) {
        this.loginEntryRepository = loginEntryRepository;
    }

    public Map<String, Long> countTotalLoggedInUsersByDateAndLocation(String startDateString, String endDateString){
        Date startDate = TicketManagerHelper.generateDateTime(startDateString);
        Date endDate = TicketManagerHelper.generateDateTime(endDateString);
        return loginEntryRepository.findByAgentLoginTimeIsBetween(startDate, endDate)
                .collect(Collectors.groupingBy(LoginEntries::getAgentLocation, Collectors.counting()));
    }

    public Map<String, Long> countTotalLoggedInUsersForDayAndGroupByLocation(){
        Date startDate = TicketManagerHelper.generateMidnightDate();
        Date endDate = new Date();
        return loginEntryRepository.findByAgentLoginTimeIsBetween(startDate, endDate)
                .collect(Collectors.groupingBy(LoginEntries::getAgentLocation, Collectors.counting()));
    }

    public Page<LoginEntries> viewAgentLoginDetails(String startDateString, String endDateString, int pageIndex, int pageSize){
        Date startDate = TicketManagerHelper.generateDateTime(startDateString);
        Date endDate = TicketManagerHelper.generateDateTime(endDateString);
        PageRequest pageRequest = new PageRequest(pageIndex, pageSize, Sort.Direction.ASC, "agentLocation", "userName", "loginDate");
        return loginEntryRepository.findByAgentLoginTimeIsBetweenOrderByLoginDateAscAgentLocationAsc(startDate, endDate, pageRequest);
    }

    public Page<LoginEntries> viewAgentLoginDetailsByUsername(String agentName, String startDateString, String endDateString, int pageIndex, int pageSize){
        Date startDate = TicketManagerHelper.generateDateTime(startDateString);
        Date endDate = TicketManagerHelper.generateDateTime(endDateString);
        PageRequest pageRequest = new PageRequest(pageIndex, pageSize, Sort.Direction.ASC, "agentLocation", "userName", "loginDate");
        return loginEntryRepository.findByUserNameLikeAndAgentLoginTimeIsBetweenOrderByLoginDateAscAgentLocationAsc(agentName, startDate, endDate, pageRequest);
    }
}
