package com.odilium.ticket.management.controller;

import com.odilium.ticket.management.mongo.entities.LoginEntries;
import com.odilium.ticket.management.service.LoginEntryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TicketManagerController {

    private LoginEntryService loginEntryService;

    @Autowired
    public TicketManagerController(LoginEntryService loginEntryService) {
        this.loginEntryService = loginEntryService;
    }

    @CrossOrigin
    @GetMapping(value = "/v1/loginUsers/countTotalLoggedInUsersByDateAndLocation")
    @ApiOperation(value = "countTotalLoggedInUsersByDateAndLocation", notes = "Count Logged In Users By Location")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
    })
    public Map<String, Long> countTotalLoggedInUsersByDateAndLocation(@RequestParam(name = "startDateString") String startDateString, @RequestParam(name = "endDateString") String endDateString){
        return loginEntryService.countTotalLoggedInUsersByDateAndLocation(startDateString, endDateString);
    }

    @CrossOrigin
    @GetMapping(value = "/v1/loginUsers/countTotalLoggedInUsersForDayAndGroupByLocation")
    @ApiOperation(value = "countTotalLoggedInUsersForDayAndGroupByLocation", notes = "Count Logged In Users For Day And Group By Location")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
    })
    public Map<String, Long> countTotalLoggedInUsersForDayAndGroupByLocation(){
        return loginEntryService.countTotalLoggedInUsersForDayAndGroupByLocation();
    }


    @CrossOrigin
    @GetMapping(value = "/v1/loginUsers/viewAgentLoginDetails")
    @ApiOperation(value = "viewAgentLoginDetails", notes = "Return Logged In Users Details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
    })
    public Page<LoginEntries> viewAgentLoginDetails(@RequestParam(name = "startDateString") String startDateString, @RequestParam(name = "endDateString") String endDateString, @RequestParam(name = "pageIndex") int pageIndex, @RequestParam(name = "pageSize") int pageSize){
        return loginEntryService.viewAgentLoginDetails(startDateString, endDateString, pageIndex, pageSize);
    }

    @CrossOrigin
    @GetMapping(value = "/v1/loginUsers/viewAgentLoginDetailsByUsername")
    @ApiOperation(value = "viewAgentLoginDetails", notes = "Return Logged In Users Details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
    })
    public Page<LoginEntries> viewAgentLoginDetailsByUsername(@RequestParam(name = "agentName") String agentName, @RequestParam(name = "startDateString") String startDateString, @RequestParam(name = "endDateString") String endDateString, @RequestParam(name = "pageIndex") int pageIndex, @RequestParam(name = "pageSize") int pageSize){
        return loginEntryService.viewAgentLoginDetailsByUsername(agentName, startDateString, endDateString, pageIndex, pageSize);
    }
}
