package com.mycompany.tennis.dto;

import com.mycompany.tennis.entity.Event;
import com.mycompany.tennis.entity.Tournament;

public class EventLightDTO extends Event {

    private Long id;
    private Short year;
    private char eventType;

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

    public char getEventType() {
        return eventType;
    }

    public void setEventType(char eventType) {
        this.eventType = eventType;
    }
}
