package com.mycompany.tennis.service;

import com.mycompany.tennis.EntityManagerHolder;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.TournamentDTO;
import com.mycompany.tennis.entity.Tournament;
import com.mycompany.tennis.repository.TournamentRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TournamentService {

    private TournamentRepositoryImpl tournamentRepository;

    public TournamentService() {
        this.tournamentRepository = new TournamentRepositoryImpl();
    }

    public TournamentDTO getTournament(Long id) {
        EntityManager em = null;
        EntityTransaction tx = null;
        Tournament tournament = null;
        TournamentDTO dto = null;
        try {
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
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
            if (em != null) {
                em.close();
            }
        }
        return dto;
    }

    public void createTournament(TournamentDTO dto) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Tournament tournament = new Tournament();
            tournament.setId(dto.getId());
            tournament.setCode(dto.getCode());
            tournament.setName(dto.getName());
            tournamentRepository.create(tournament);
            dto.setId(tournament.getId());
            tx.commit();
        } catch (Exception e) {
            if ( tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void deleteTournament(Long id) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();

            tournamentRepository.delete(id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
