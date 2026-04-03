package com.mycompany.tennis.service;

import com.mycompany.tennis.entity.Player;
import com.mycompany.tennis.repository.PlayerRepositoryImpl;

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
}
