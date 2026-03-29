package com.mycompany.tennis;

import com.mycompany.tennis.entity.Player;
import com.mycompany.tennis.repository.PlayerRepositoryImpl;

import java.util.List;

public class TestConnexion {
    public static void main(String... args) {
        PlayerRepositoryImpl playerRepository = new PlayerRepositoryImpl();
        List<Player> players = playerRepository.getPlayersList();

        for (Player player : players) {
            System.out.println("Nom " + player.getLastname() + " , Prénom : " + player.getFirstname());
        }
    }
}