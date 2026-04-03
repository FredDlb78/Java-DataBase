package com.mycompany.tennis.service;

import com.mycompany.tennis.entity.Tournament;
import com.mycompany.tennis.repository.TournamentRepositoryImpl;

public class TournamentService {

    private TournamentRepositoryImpl tournamentRepository;

    public TournamentService() {
        this.tournamentRepository = new TournamentRepositoryImpl();
    }

    public void createTournament(Tournament tournament) {
        tournamentRepository.create(tournament);
    }

    public Tournament getTournament(Long id) {
        return tournamentRepository.getById(id);
    }
}
