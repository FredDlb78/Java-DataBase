package com.mycompany.tennis;

import com.mycompany.tennis.dto.TournamentDTO;
import com.mycompany.tennis.entity.*;
import com.mycompany.tennis.service.MatchService;
import com.mycompany.tennis.service.TournamentService;

public class Main {
    public static void main(String[] args) {

        TournamentService tournamentService = new TournamentService();

        for (long i = 1; i <= 8; i++) {
            TournamentDTO tournament = tournamentService.getTournament(i);
            if (tournament != null) {

                System.out.println("Le nom du tournoi à l'id " + tournament.getId() + " est " + tournament.getName());
            }
        }

    }
}