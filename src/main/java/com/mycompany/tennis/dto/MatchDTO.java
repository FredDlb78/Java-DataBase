package com.mycompany.tennis.dto;

public class MatchDTO {

    private Long id;
    private PlayerDTO winner;
    private PlayerDTO finalist;
    private EventFullDTO event;
    private ScoreFullDTO score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlayerDTO getWinner() {
        return winner;
    }

    public void setWinner(PlayerDTO winner) {
        this.winner = winner;
    }

    public PlayerDTO getFinalist() {
        return finalist;
    }

    public void setFinalist(PlayerDTO finalist) {
        this.finalist = finalist;
    }

    public EventFullDTO getEvent() {
        return event;
    }

    public void setEvent(EventFullDTO event) {
        this.event = event;
    }

    public ScoreFullDTO getScore() {
        return score;
    }

    public void setScore(ScoreFullDTO score) {
        this.score = score;
    }
}
