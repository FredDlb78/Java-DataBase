package com.mycompany.tennis.service;

import com.mycompany.tennis.dao.MatchDaoImpl;
import com.mycompany.tennis.entity.Match;
import com.mycompany.tennis.repository.MatchRepositoryImpl;
import com.mycompany.tennis.repository.ScoreRepositoryImpl;

public class MatchService {

    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;
    private MatchDaoImpl matchDao;

    public MatchService() {
        this.scoreRepository  = new ScoreRepositoryImpl();
        this.matchRepository = new MatchRepositoryImpl();
        this.matchDao = new MatchDaoImpl();
    }

    public void saveNewMatch(Match match) {
        matchDao.createMatchWithScore(match);
    }
}
