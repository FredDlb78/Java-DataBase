package com.mycompany.tennis.service;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dao.MatchDaoImpl;
import com.mycompany.tennis.dto.*;
import com.mycompany.tennis.entity.Match;
import com.mycompany.tennis.entity.Score;
import com.mycompany.tennis.entity.Tournament;
import com.mycompany.tennis.repository.MatchRepositoryImpl;
import com.mycompany.tennis.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;
    private MatchDaoImpl matchDao;
    private MatchDTO matchDTO;

    public MatchService() {
        this.scoreRepository = new ScoreRepositoryImpl();
        this.matchRepository = new MatchRepositoryImpl();
        this.matchDao = new MatchDaoImpl();
        this.matchDTO = new MatchDTO();
    }

    public MatchDTO getMatch(Long id) {
        Session session = null;
        Transaction tx = null;
        Match match = null;
        MatchDTO dto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            match = matchRepository.getById(id);

            dto = new MatchDTO();
            dto.setId(match.getId());

            PlayerDTO winnerDTO = new PlayerDTO();
            winnerDTO.setId(match.getWinner().getId());
            winnerDTO.setLastname(match.getWinner().getLastname());
            winnerDTO.setFirstname(match.getWinner().getFirstname());
            winnerDTO.setSex(match.getWinner().getSex());
            dto.setWinner(winnerDTO);

            PlayerDTO finalistDTO = new PlayerDTO();
            finalistDTO.setId(match.getFinalist().getId());
            finalistDTO.setLastname(match.getFinalist().getLastname());
            finalistDTO.setFirstname(match.getFinalist().getFirstname());
            finalistDTO.setSex(match.getFinalist().getSex());
            dto.setFinalist(finalistDTO);

            EventFullDTO eventDTO = new EventFullDTO();
            eventDTO.setId(match.getEvent().getId());
            eventDTO.setYear(match.getEvent().getYear());
            eventDTO.setEventType(match.getEvent().getEventType());

            TournamentDTO tournamentDTO = new TournamentDTO();
            Tournament tournament = match.getEvent().getTournament();
            tournamentDTO.setId(tournament.getId());
            tournamentDTO.setCode(tournament.getCode());
            tournamentDTO.setName(tournament.getName());
            eventDTO.setTournamentDTO(tournamentDTO);

            dto.setEvent(eventDTO);

            ScoreFullDTO scoreDTO = new ScoreFullDTO();
            Score score = match.getScore();
            scoreDTO.setId(score.getId());
            scoreDTO.setSet1(score.getSet1());
            scoreDTO.setSet2(score.getSet2());
            scoreDTO.setSet3(score.getSet3());
            scoreDTO.setSet4(score.getSet4());
            scoreDTO.setSet5(score.getSet5());

            dto.setScore(scoreDTO);
            scoreDTO.setMatch(matchDTO);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return dto;
    }

    public void saveNewMatch(Match match) {
        matchDao.createMatchWithScore(match);
    }
}
