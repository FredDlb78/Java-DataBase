package com.mycompany.tennis;

import com.mycompany.tennis.entity.Tournament;
import com.mycompany.tennis.repository.TournamentRepositoryImpl;

import java.util.List;


public class TestConnexion {
    public static void main(String... args) {
        TournamentRepositoryImpl tournamentRepository = new TournamentRepositoryImpl();
        List<Tournament> tournaments = tournamentRepository.getTournamentList();

        for (Tournament tournament : tournaments) {
            System.out.println("id: " + tournament.getId() + ", Nom :" + tournament.getName() + ", Code: " + tournament.getCode());
        }
    }
}