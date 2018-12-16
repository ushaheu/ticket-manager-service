package com.odilium.ticket.management.helper;

import java.io.Serializable;

public class LoginEntryAnalyticsHelper implements Serializable {

    private String agentLocation;
    private int agentCount;

    public LoginEntryAnalyticsHelper() {
    }

    public LoginEntryAnalyticsHelper(String agentLocation, int agentCount) {
        this.agentLocation = agentLocation;
        this.agentCount = agentCount;
    }

    public String getAgentLocation() {
        return agentLocation;
    }

    public void setAgentLocation(String agentLocation) {
        this.agentLocation = agentLocation;
    }

    public int getAgentCount() {
        return agentCount;
    }

    public void setAgentCount(int agentCount) {
        this.agentCount = agentCount;
    }

    @Override
    public String toString() {
        return "LoginEntryAnalyticsHelper{" +
                "agentLocation='" + agentLocation + '\'' +
                ", agentCount=" + agentCount +
                '}';
    }
}
