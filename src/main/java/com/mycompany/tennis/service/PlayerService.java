package com.mycompany.tennis.service;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.PlayerDTO;
import com.mycompany.tennis.entity.Player;
import com.mycompany.tennis.repository.PlayerRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Session session = null;
        Transaction tx = null;
        List<PlayerDTO> playerDTOS = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

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
            if (session != null) {
                session.close();
            }
        }
        return playerDTOS;
    }
}
