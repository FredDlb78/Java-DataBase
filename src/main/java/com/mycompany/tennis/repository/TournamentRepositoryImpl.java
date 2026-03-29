package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.entity.Player;
import com.mycompany.tennis.entity.Tournament;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournamentRepositoryImpl {

    public void create(Tournament tournament) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO TOURNOI (NOM, CODE) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, tournament.getName());
            preparedStatement.setString(2, tournament.getCode());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                tournament.setId(rs.getLong(1));
            }

            conn.commit();
            preparedStatement.close();

            System.out.println("Tournoi créé.");
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
        Connection conn = null;
        Tournament tournament = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT NOM, CODE FROM TOURNOI WHERE ID=?");
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tournament = new Tournament();
                tournament.setId(id);
                tournament.setName(rs.getString("NOM"));
                tournament.setCode(rs.getString("CODE"));
            }

            conn.commit();
            preparedStatement.close();

            System.out.println("Tournoi lu.");
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