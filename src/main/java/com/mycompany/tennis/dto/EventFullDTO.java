package com.mycompany.tennis.dto;

import com.mycompany.tennis.entity.Event;

public class EventFullDTO extends Event {

    private Long id;
    private Short year;
    private char eventType;
    private TournamentDTO tournamentDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getYear() {
        return year;
    }

    public TournamentDTO getTournamentDTO() {
        return tournamentDTO;
    }

    public void setTournamentDTO(TournamentDTO tournamentDTO) {
        this.tournamentDTO = tournamentDTO;
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
