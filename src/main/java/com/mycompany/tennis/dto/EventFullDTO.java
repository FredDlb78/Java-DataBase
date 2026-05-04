package com.mycompany.tennis.dto;

import java.util.Set;

public class EventFullDTO {

    private Long id;
    private Short year;
    private char eventType;
    private TournamentDTO tournamentDTO;
    private Set<PlayerDTO> participants;

    public Set<PlayerDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<PlayerDTO> participants) {
        this.participants = participants;
    }

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
