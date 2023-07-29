package com.luxsoloari.riotapidemo.rest;

import com.luxsoloari.riotapidemo.entity.Player;
import com.luxsoloari.riotapidemo.manager.PlayerManager;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class PlayerController {
    private static final String BASE_PLAYER_PATH = "/players";
    private final PlayerManager playerManager;

    public PlayerController(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @GetMapping(BASE_PLAYER_PATH + "/about")
    public String getInfo() {
        return "This controller handles Player operations.";
    }

    @GetMapping(BASE_PLAYER_PATH)
    public ResponseEntity<CollectionModel<EntityModel<Player>>> getAllPlayers() {
        List<EntityModel<Player>> players = this.playerManager.getAll().stream()
                .map(player -> EntityModel.of(player,
                        linkTo(PlayerController.class).slash(BASE_PLAYER_PATH).slash(player.getId()).withSelfRel(),
                        linkTo(PlayerController.class).slash(BASE_PLAYER_PATH).withRel("players")))
                .toList();
        return ResponseEntity.ok(CollectionModel.of(players,
                linkTo(PlayerController.class).slash(BASE_PLAYER_PATH).withSelfRel()));
    }

    @GetMapping(BASE_PLAYER_PATH + "/{id}")
    public ResponseEntity<EntityModel<Player>> getById(@PathVariable Integer id) {
        Player player = this.playerManager.getById(id);

        return ResponseEntity.ok(EntityModel.of(player,
                linkTo(PlayerController.class).slash(BASE_PLAYER_PATH).slash(player.getId()).withSelfRel(),
                linkTo(PlayerController.class).slash(BASE_PLAYER_PATH).withRel("players")));
    }

    @PostMapping(BASE_PLAYER_PATH)
    public ResponseEntity<EntityModel<Player>> savePlayer(@RequestBody Player newPlayer) {
        Player player = this.playerManager.save(newPlayer);

        // return a 201 created status code with the location of the new resource and the resource itself in the body.
        return ResponseEntity.created(linkTo(PlayerController.class).slash(BASE_PLAYER_PATH).slash(player.getId()).toUri())
                .body(EntityModel.of(player,
                        linkTo(PlayerController.class).slash(BASE_PLAYER_PATH).slash(player.getId()).withSelfRel(),
                        linkTo(PlayerController.class).slash(BASE_PLAYER_PATH).withRel("players")));
    }

    @PutMapping(BASE_PLAYER_PATH + "/{id}")
    public ResponseEntity<EntityModel<Player>> updatePlayer(@RequestBody Player newPlayer, @PathVariable Integer id) {
        Player player = this.playerManager.update(newPlayer, id);

        return ResponseEntity.ok(EntityModel.of(player,
                linkTo(PlayerController.class).slash(BASE_PLAYER_PATH).slash(player.getId()).withSelfRel(),
                linkTo(PlayerController.class).slash(BASE_PLAYER_PATH).withRel("players")));
    }

    @DeleteMapping(BASE_PLAYER_PATH + "/{id}")
    public ResponseEntity<EntityModel<Player>> deletePlayer(@PathVariable Integer id) {
        this.playerManager.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
