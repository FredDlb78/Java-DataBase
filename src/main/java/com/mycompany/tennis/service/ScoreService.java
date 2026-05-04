package com.mycompany.tennis.service;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.*;
import com.mycompany.tennis.entity.Event;
import com.mycompany.tennis.entity.Score;
import com.mycompany.tennis.entity.Tournament;
import com.mycompany.tennis.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {

    private ScoreRepositoryImpl scoreRepository;
    private ScoreFullDTO scoreDTO;

    public ScoreService() {
        this.scoreRepository = new ScoreRepositoryImpl();
        this.scoreDTO = new ScoreFullDTO();
    }

    public ScoreFullDTO getScore(Long id) {
        Session session = null;
        Transaction tx = null;
        Score score = null;
        ScoreFullDTO dto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            score = scoreRepository.getById(id);

            dto = new ScoreFullDTO();
            dto.setId(score.getId());
            dto.setSet1(score.getSet1());
            dto.setSet2(score.getSet2());
            dto.setSet3(score.getSet3());
            dto.setSet4(score.getSet4());
            dto.setSet5(score.getSet5());

            MatchDTO match = new MatchDTO();
            match.setId(score.getMatch().getId());

            PlayerDTO winnerDTO = new PlayerDTO();
            winnerDTO.setId(score.getMatch().getWinner().getId());
            winnerDTO.setFirstname(score.getMatch().getWinner().getFirstname());
            winnerDTO.setLastname(score.getMatch().getWinner().getLastname());
            winnerDTO.setSex(score.getMatch().getWinner().getSex());
            match.setWinner(winnerDTO);

            PlayerDTO finalistDTO = new PlayerDTO();
            finalistDTO.setId(score.getMatch().getFinalist().getId());
            finalistDTO.setFirstname(score.getMatch().getFinalist().getFirstname());
            finalistDTO.setLastname(score.getMatch().getFinalist().getLastname());
            finalistDTO.setSex(score.getMatch().getFinalist().getSex());
            match.setFinalist(finalistDTO);

            dto.setMatch(match);

            EventFullDTO eventDTO = new EventFullDTO();
            Event event = score.getMatch().getEvent();
            eventDTO.setId(event.getId());
            eventDTO.setYear(event.getYear());
            eventDTO.setEventType(event.getEventType());

            TournamentDTO tournamentDTO = new TournamentDTO();
            Tournament tournament = event.getTournament();
            tournamentDTO.setId(tournament.getId());
            tournamentDTO.setCode(tournament.getCode());
            tournamentDTO.setName(tournament.getName());
            eventDTO.setTournamentDTO(tournamentDTO);

            match.setEvent(eventDTO);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }

        return dto;
    }

}
