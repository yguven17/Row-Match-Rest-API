package com.rowmatch.model;

import javax.persistence.*;

@Entity
@Table(name = "leaderboards")
public class Leaderboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "tournament_name")
    private String tournamentName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }


    public void setTournament(Tournament tournament) {

    }

    public void setUser(User user) {
    }

    public void setPoints(int i) {
    }

    public int getPoints() {
        return 0;
    }

    public User getUser() {
        return null;
    }


    public int getScore() {
        return 0;
    }
}
