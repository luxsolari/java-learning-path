package com.luxsoloari.riotapidemo.manager;

import com.luxsoloari.riotapidemo.client.OriannaClient;
import com.luxsoloari.riotapidemo.entity.Player;
import com.luxsoloari.riotapidemo.exception.PlayerAlreadyExistsException;
import com.luxsoloari.riotapidemo.exception.PlayerNotFoundException;
import com.luxsoloari.riotapidemo.repository.PlayerRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PlayerManager {
    private final PlayerRepository repository;
    private final OriannaClient oriannaClient;

    public PlayerManager(PlayerRepository repository, OriannaClient oriannaClient) {
        this.repository = repository;
        this.oriannaClient = oriannaClient;
    }

    public List<Player> getAll() {
        // sort alphabetically by player name
        return this.repository.findAll(Sort.by("id"));
    }

    public Player getById(Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + " not found."));
    }

    public Player save(Player newPlayer) {
        var summoner = this.oriannaClient.getSummonerByName(newPlayer.getPlayerName());

        // map summoner object to entity object
        newPlayer.setRiotId(summoner.getId());
        newPlayer.setPuuid(summoner.getPuuid());
        newPlayer.setPlayerName(summoner.getName());
        newPlayer.setRevisionDate(summoner.getUpdated().getMillis());
        newPlayer.setSummonerLevel(summoner.getLevel());
        newPlayer.setProfileIconId(summoner.getProfileIcon().getId());

        if (!summoner.exists())
            throw new PlayerNotFoundException("Player with name " + newPlayer.getPlayerName() + " not found.");
        try {
            return this.repository.save(newPlayer);
        } catch (DataIntegrityViolationException e) {
            throw new PlayerAlreadyExistsException("Player with name " + newPlayer.getPlayerName() + " already exists.");
        }
    }

    public Player update(Player newPlayer, Integer id) {
        return this.repository.findById(id)
                .map(player -> {
                    player.setId(id);
                    player.setPlayerName(newPlayer.getPlayerName());
                    return this.repository.save(player);
                }).orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + " not found."));
    }

    public void deleteById(Integer id) {
        Player p = this.repository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + " not found."));
        this.repository.deleteById(p.getId());
    }
}
