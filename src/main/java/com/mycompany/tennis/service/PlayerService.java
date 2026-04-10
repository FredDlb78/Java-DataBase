package com.mycompany.tennis.service;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Player;
import com.mycompany.tennis.repository.PlayerRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
