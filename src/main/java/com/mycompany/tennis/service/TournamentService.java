package com.mycompany.tennis.service;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.TournamentDTO;
import com.mycompany.tennis.entity.Tournament;
import com.mycompany.tennis.repository.TournamentRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TournamentService {

    private TournamentRepositoryImpl tournamentRepository;

    public TournamentService() {
        this.tournamentRepository = new TournamentRepositoryImpl();
    }

    public TournamentDTO getTournament(Long id) {
        Session session = null;
        Transaction tx = null;
        Tournament tournament = null;
        TournamentDTO dto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            tournament = tournamentRepository.getById(id);
            dto = new TournamentDTO();
            dto.setId(tournament.getId());
            dto.setCode(tournament.getCode());
            dto.setName(tournament.getName());
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

    public void createTournament(TournamentDTO dto) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Tournament tournament = new Tournament();
            tournament.setId(dto.getId());
            tournament.setCode(dto.getCode());
            tournament.setName(dto.getName());
            tournamentRepository.create(tournament);
            tx.commit();
        } catch (Exception e) {
            if ( tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteTournament(Long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            tournamentRepository.delete(id);
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
    }
}
