package com.mycompany.tennis.entity;

public class Event {

    private Long id;
    private Short year;
    private Tournament tournament;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public char getEventType() {
        return eventType;
    }

    public void setEventType(char eventType) {
        this.eventType = eventType;
    }

    private char eventType;

}
