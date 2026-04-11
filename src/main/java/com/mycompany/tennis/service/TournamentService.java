package com.mycompany.tennis.service;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Tournament;
import com.mycompany.tennis.repository.TournamentRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
