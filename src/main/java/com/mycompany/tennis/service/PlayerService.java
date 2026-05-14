package com.mycompany.tennis.service;

import com.mycompany.tennis.EntityManagerHolder;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.PlayerDTO;
import com.mycompany.tennis.entity.Player;
import com.mycompany.tennis.repository.PlayerRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class PlayerService {

    private PlayerRepositoryImpl playerRepository;

    public PlayerService() {
        this.playerRepository = new PlayerRepositoryImpl();
    }

    public void createPlayer(Player player) {
        playerRepository.create(player);
    }

    public Player getPlayer(Long id) {
        return playerRepository.getById(id);
    }

    public void updatePlayerLastname(Long id, String newLastname) {
        Session session = null;
        Transaction tx = null;
        Player player = getPlayer(id);

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            player.setLastname(newLastname);
            Player player2 = (Player)session.merge(player);
            session.merge(player);
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
    }

    public void updatePlayerSex(Long id, char newSex) {
        Session session = null;
        Transaction tx = null;
        Player player = getPlayer(id);

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            player.setSex(newSex);
            Player player2 = (Player)session.merge(player);
            session.merge(player);
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
    }

    public void deletePlayer(Long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            playerRepository.delete(id);
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

    public List<PlayerDTO> getPlayersList(char sex) {
        EntityManager em = null;
        EntityTransaction tx = null;
        List<PlayerDTO> playerDTOS = new ArrayList<>();
        try {
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();

            List<Player> players = playerRepository.getPlayersList(sex);

            for (Player player: players) {
                final PlayerDTO playerDTO = new PlayerDTO();
                playerDTO.setId(player.getId());
                playerDTO.setLastname(player.getLastname());
                playerDTO.setFirstname(player.getFirstname());
                playerDTO.setSex(player.getSex());
                playerDTOS.add(playerDTO);
            }

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
        return playerDTOS;
    }
}
