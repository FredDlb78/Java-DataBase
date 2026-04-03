package com.mycompany.tennis.entity;

public class Match {

    private Long id;
    private Player winner;
    private Player finalist;
    private Event event;
    private Score score;

    public Long getId() {
        return id;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getFinalist() {
        return finalist;
    }

    public void setFinalist(Player finalist) {
        this.finalist = finalist;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
