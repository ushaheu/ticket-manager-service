package com.odilium.ticket.management.mongo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "login-entries")
public class LoginEntries {

    @Id
    private String id;
    private String userName;
    private String loginDate;
    private Long ticketCount;
    private String agentLocation;
    private Date agentLoginTime;
    private Date agentLastTicketProcessedTime;

    public LoginEntries() {
    }

    public LoginEntries(String userName, String loginDate) {
        this.userName = userName;
        this.loginDate = loginDate;
    }

    public LoginEntries(String id, String userName, String loginDate, Long ticketCount, String agentLocation) {
        this.id = id;
        this.userName = userName;
        this.loginDate = loginDate;
        this.ticketCount = ticketCount;
        this.agentLocation = agentLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public Long getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Long ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getAgentLocation() {
        return agentLocation;
    }

    public void setAgentLocation(String agentLocation) {
        this.agentLocation = agentLocation;
    }

    public Date getAgentLoginTime() {
        return agentLoginTime;
    }

    public void setAgentLoginTime(Date agentLoginTime) {
        this.agentLoginTime = agentLoginTime;
    }

    public Date getAgentLastTicketProcessedTime() {
        return agentLastTicketProcessedTime;
    }

    public void setAgentLastTicketProcessedTime(Date agentLastTicketProcessedTime) {
        this.agentLastTicketProcessedTime = agentLastTicketProcessedTime;
    }

    @Override
    public String toString() {
        return "LoginEntries{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", loginDate='" + loginDate + '\'' +
                ", ticketCount=" + ticketCount +
                ", agentLocation='" + agentLocation + '\'' +
                '}';
    }
}
