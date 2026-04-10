package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Player;
import com.mycompany.tennis.entity.Tournament;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournamentRepositoryImpl {

    public void create(Tournament tournament) {
        Session session = null;
        Transaction tx = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(tournament);
            tx.commit();

            System.out.println("Tournoi créé.");
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

    public void update(Tournament tournament) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE TOURNOI SET NOM=?, CODE=? WHERE ID=?");

            preparedStatement.setString(1, tournament.getName());
            preparedStatement.setString(2, tournament.getCode());
            preparedStatement.setLong(3, tournament.getId());

            preparedStatement.executeUpdate();
            conn.commit();
            preparedStatement.close();

            System.out.println("Tournoi modifié.");
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

            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM TOURNOI WHERE ID=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            conn.commit();
            preparedStatement.close();

            System.out.println("Tournoi supprimé.");
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

    public Tournament getById(Long id) {
        Tournament tournament = null;
        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tournament = session.get(Tournament.class, id);

            System.out.println("Tournoi lu.");
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return tournament;
    }

    public List<Tournament> getTournamentList() {
        Connection conn = null;
        List<Tournament> tournaments = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID, NOM, CODE FROM TOURNOI");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Tournament tournament = new Tournament();
                tournament.setId(rs.getLong("ID"));
                tournament.setName(rs.getString("NOM"));
                tournament.setCode(rs.getString("CODE"));
                tournaments.add(tournament);
            }

            conn.commit();
            preparedStatement.close();

            System.out.println("Tournois lus.");
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
        return tournaments;
    }

}