package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerRepositoryImpl {

    public void create(Player player) {
        Session session = null;
        Transaction tx = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(player);
            tx.commit();

            System.out.println("Joueur créé.");
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

    public void update(Player player) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=? , SEXE=? WHERE ID=?");

            preparedStatement.setString(1, player.getLastname());
            preparedStatement.setString(2, player.getFirstname());
            preparedStatement.setString(3, player.getSex().toString());
            preparedStatement.setLong(4, player.getId());

            preparedStatement.executeUpdate();

            conn.commit();

            //registeredModifications.close();
            preparedStatement.close();

            System.out.println("Joueur modifié.");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Long id) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM JOUEUR WHERE ID=?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            conn.commit();

            //registeredModifications.close();
            preparedStatement.close();

            System.out.println("Joueur supprimé.");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Player getById(Long id) {
        Player player = null;
        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            player = session.get(Player.class, id);

            System.out.println("Joueur lu.");
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return player;
    }

    public List<Player> getPlayersList() {
        Connection conn = null;
        List<Player> players = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID, NOM, PRENOM, SEXE FROM JOUEUR");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Player player = new Player();
                player.setId(rs.getLong("ID"));
                player.setLastname(rs.getString("NOM"));
                player.setFirstname(rs.getString("PRENOM"));
                player.setSex(rs.getString("SEXE").charAt(0));
                players.add(player);
            }

            conn.commit();

            //registeredModifications.close();
            preparedStatement.close();

            System.out.println("Joueur lu.");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return players;
    }
}
