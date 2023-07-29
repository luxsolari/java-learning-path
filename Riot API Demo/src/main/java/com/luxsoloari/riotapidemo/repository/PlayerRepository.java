package com.luxsoloari.riotapidemo.repository;

import com.luxsoloari.riotapidemo.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    @Override
    <S extends Player> List<S> findAll(@NotNull Example<S> example);
    @Override
    Optional<Player> findById(Integer integer);
    @Override
    <S extends Player> S save(S s);
    @Override
    void deleteById(Integer integer);

}
