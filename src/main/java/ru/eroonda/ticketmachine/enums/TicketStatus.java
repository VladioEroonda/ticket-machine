package ru.eroonda.ticketmachine.enums;

public enum TicketStatus {
    NEW,
    IN_PROGRESS,
    FINISHED,
    CONFIRMED,
    RENEWED,
    INFORMATION_REQUESTED;

    public static String getStringFromIndex(int i){
        return TicketStatus.values()[i].toString();//TODO: test it, i-1 probably
    }
}

