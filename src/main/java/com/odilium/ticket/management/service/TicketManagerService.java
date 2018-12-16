package com.odilium.ticket.management.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odilium.ticket.management.cache.entities.TicketInformation;
import com.odilium.ticket.management.helper.LoginEntryAnalyticsHelper;
import com.odilium.ticket.management.mongo.entities.LoginEntries;
import com.odilium.ticket.management.mongo.entities.TicketLogs;
import com.odilium.ticket.management.repositories.LoginEntryRepository;
import com.odilium.ticket.management.repositories.TicketInformationRepository;
import com.odilium.ticket.management.repositories.TicketLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Service
public class TicketManagerService {

    private TicketInformationRepository ticketInformationRepository;
    private TicketLogsRepository ticketLogsRepository;
    private LoginEntryRepository loginEntryRepository;
    private static Logger logger = Logger.getLogger("TicketManagerService");

    @Value(value = "${activemq.analytics.queue}")
    private String analyticsQueue;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public TicketManagerService(
                                TicketInformationRepository ticketInformationRepository,
                                TicketLogsRepository ticketLogsRepository,
                                LoginEntryRepository loginEntryRepository, SimpMessagingTemplate simpMessagingTemplate) {
        this.ticketInformationRepository = ticketInformationRepository;
        this.ticketLogsRepository = ticketLogsRepository;
        this.loginEntryRepository = loginEntryRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @ServiceActivator(inputChannel = "ticket-manager-logs")
    public void writeTransactionsToMongoDB(Message<String> ticketManagerLogsMessage) {
        try {
            TicketLogs ticketLogs = new ObjectMapper().readValue(ticketManagerLogsMessage.getPayload(), TicketLogs.class);
            ticketLogsRepository.save(ticketLogs);
            writeLoginEntryTransactionsToMongo(ticketLogs.getOperatorAgentName(), ticketLogs.getTicketIssuedDateTime(), ticketLogs.getTicketID());
        }catch (Exception ex){
            logger.severe(ex.getMessage());
        }
    }

    @ServiceActivator(inputChannel = "ticket-status")
    public void writeTransactionsToRedis(Message<String> ticketStatusMessage) {
        try {
            TicketInformation ticketInformation = new ObjectMapper().readValue(ticketStatusMessage.getPayload(), TicketInformation.class);
            ticketInformationRepository.save(ticketInformation);
        }catch (Exception ex){
            logger.severe(ex.getMessage());
        }
    }

    public void writeLoginEntryTransactionsToMongo(String userName, Date ticketCreationDate, String ticketID) {
        try {
            String agentLocation = ticketID.startsWith("CCLOS")? "LAGOS": "ABUJA";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String loginDateString = simpleDateFormat.format(ticketCreationDate);
            LoginEntries loginEntries = loginEntryRepository.findByUserNameAndLoginDateAndAgentLocation(userName, loginDateString, agentLocation);
            if(loginEntries == null){
                LoginEntries loginEntriesObject = new LoginEntries();
                loginEntriesObject.setLoginDate(loginDateString);
                loginEntriesObject.setTicketCount(1L);
                loginEntriesObject.setAgentLocation(agentLocation);
                loginEntriesObject.setUserName(userName);
                loginEntriesObject.setAgentLoginTime(ticketCreationDate);
                loginEntriesObject.setAgentLastTicketProcessedTime(ticketCreationDate);
                loginEntryRepository.save(loginEntriesObject);
                LoginEntryAnalyticsHelper loginEntryAnalyticsHelper = new LoginEntryAnalyticsHelper();
                loginEntryAnalyticsHelper.setAgentLocation(agentLocation);
                loginEntryAnalyticsHelper.setAgentCount(1);
                simpMessagingTemplate.convertAndSend(analyticsQueue, new ObjectMapper().writeValueAsString(loginEntryAnalyticsHelper));
            }else {
                Long ticketCount = loginEntries.getTicketCount();
                loginEntries.setTicketCount(ticketCount + 1);
                loginEntries.setAgentLastTicketProcessedTime(ticketCreationDate);
                loginEntryRepository.save(loginEntries);
            }
        }catch (Exception ex){
            logger.severe(ex.getMessage());
        }
    }
}
