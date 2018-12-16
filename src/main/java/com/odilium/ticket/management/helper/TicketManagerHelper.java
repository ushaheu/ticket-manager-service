package com.odilium.ticket.management.helper;

import com.odilium.ticket.management.cache.entities.TicketInformation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

public class TicketManagerHelper {

    private static Logger logger = Logger.getLogger("TicketManagerHelper");

    private TicketManagerHelper() {
    }

    public static String generateTodaysDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date today = new Date();
        return simpleDateFormat.format(today);
    }

    public static Date generateDateTime(String dateString){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            logger.severe(e.getMessage());
        }
        return dateTime;
    }

    public static TicketInformation createCacheTicketInformation(String ticketId, String dateOfEscalation){
        TicketInformation ticketInformation = new TicketInformation();
        ticketInformation.setTicketId(ticketId);
        ticketInformation.setDateOfEscalation(dateOfEscalation);
        return ticketInformation;
    }

    public static Long generateTransactionID(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS");
        LocalDateTime date = LocalDateTime.now();
        return Long.parseLong(dateTimeFormatter.format(date));
    }

    public static Date generateMidnightDate(){
        Calendar date = new GregorianCalendar();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }
}
