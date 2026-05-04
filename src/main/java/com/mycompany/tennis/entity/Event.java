package com.mycompany.tennis.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "EPREUVE")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ANNEE")
    @Type(type = "short")
    private Short year;
    @Column(name = "TYPE_EPREUVE", nullable = true, length = 2000)
    private char eventType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TOURNOI")
    private Tournament tournament;

    @ManyToMany
    @JoinTable(
            name = "PARTICIPANTS",
            joinColumns = {@JoinColumn(name = "ID_EPREUVE")},
            inverseJoinColumns = {@JoinColumn(name = "ID_JOUEUR")}
    )
    private Set<Player> participants;

    public Set<Player> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Player> participants) {
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
}
