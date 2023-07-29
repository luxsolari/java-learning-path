package com.luxsoloari.riotapidemo.entity;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.Objects;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    @Column(name = "riot_id")
    private String riotId;
    @NonNull
    @Column(name = "puuid")
    private String puuid;
    @Column(name = "player_name")
    private String playerName;
    @Column(name = "revision_date")
    private Long revisionDate;
    @Column(name = "summoner_level")
    private Integer summonerLevel;
    @Column(name = "profile_icon_id")
    private Integer profileIconId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NonNull
    public String getRiotId() {
        return riotId;
    }

    public void setRiotId(@NonNull String riotId) {
        this.riotId = riotId;
    }

    @NonNull
    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(@NonNull String puuid) {
        this.puuid = puuid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public Integer getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(Integer summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public Integer getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(Integer profileIconId) {
        this.profileIconId = profileIconId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) && Objects.equals(riotId, player.riotId) && Objects.equals(puuid, player.puuid) && Objects.equals(playerName, player.playerName) && Objects.equals(revisionDate, player.revisionDate) && Objects.equals(summonerLevel, player.summonerLevel) && Objects.equals(profileIconId, player.profileIconId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, riotId, puuid, playerName, revisionDate, summonerLevel, profileIconId);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", riotId=" + riotId +
                ", puuid='" + puuid + '\'' +
                ", playerName='" + playerName + '\'' +
                ", revisionDate=" + revisionDate +
                ", summonerLevel=" + summonerLevel +
                ", profileIconId=" + profileIconId +
                '}';
    }
}
