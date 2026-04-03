package com.mycompany.tennis;

import com.mycompany.tennis.entity.*;
import com.mycompany.tennis.service.MatchService;

public class Main {
    public static void main(String[] args) {

        MatchService matchService = new MatchService();
        Match match = new Match();
        Score score = new Score();

        score.setSet1((byte) 3);
        score.setSet2((byte) 4);
        score.setSet3((byte) 6);

        match.setScore(score);
        score.setMatch(match);

        Player federer = new Player();
        federer.setId(32L);

        Player murray = new Player();
        murray.setId(34L);

        match.setWinner(federer);
        match.setFinalist(murray);

        Event event = new Event();
        event.setId(9L);

        match.setEvent(event);


        matchService.saveNewMatch(match);

        System.out.println("L'id du match créé est : " + match.getId());

    }
}