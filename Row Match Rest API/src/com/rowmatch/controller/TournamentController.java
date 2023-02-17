package com.rowmatch.controller;

import com.rowmatch.model.Leaderboard;
import com.rowmatch.model.Tournament;
import com.rowmatch.model.User;
import com.rowmatch.service.LeaderboardService;
import com.rowmatch.util.CoinUtil;
import com.rowmatch.service.TournamentService;
import com.rowmatch.service.UserService;
import com.rowmatch.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;
    private final LeaderboardService leaderboardService;
    private UserService userService;


    public Tournament enterTournament(long userId) {
        User user = userService.getUserById(userId);
        if (!isEligibleForTournament(user) && !TimeUtil.isTournamentStarted()) {
            throw new IllegalArgumentException("User is not eligible for the tournament.");
        }

        Tournament tournament = tournamentService.getCurrentTournament();
        tournamentService.enterTournament(user, tournament);
        return tournament;
    }

    public User claimReward(long userId, long tournamentId) {
        User user = userService.getUserById(userId);
        Tournament tournament = tournamentService.findTournamentById(tournamentId);
        if (!tournament.isActive() || !tournament.isHasUser()) {
            throw new IllegalArgumentException("User is not eligible for claiming the reward.");
        }

        int rank = tournamentService.getRank(user, tournament);
        int reward = CoinUtil.getReward(rank, tournamentService.getTotalPlayers());
        user.setCoins(user.getCoins() + reward);
        userService.updateUser(user);
        return user;
    }

    public int getRank(long userId, long tournamentId) {
        User user = userService.getUserById(userId);
        Tournament tournament = tournamentService.findTournamentById(tournamentId);
        return tournamentService.getRank(user, tournament);
    }

    private boolean isEligibleForTournament(User user) {
        return user.getLevel() >= 20 && user.getCoins() >= 1000;
    }

    public List<Leaderboard> getLeaderboard(long tournamentId) {
        Tournament tournament = tournamentService.findTournamentById(tournamentId);
        Leaderboard leaderboard = (Leaderboard) leaderboardService.getLeaderboards(tournament);
        return (List<Leaderboard>) leaderboard;
    }

    @Autowired
    public TournamentController(TournamentService tournamentService, LeaderboardService leaderboardService) {
        this.tournamentService = tournamentService;
        this.leaderboardService = leaderboardService;
    }

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @PostMapping
    public Tournament addTournament(@RequestBody Tournament tournament) {
        return tournamentService.addTournament(tournament);
    }

    @PutMapping("/{id}")
    public Tournament updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
        return tournamentService.updateTournament(tournament);
    }

    @DeleteMapping("/{id}")
    public void deleteTournament(@PathVariable Tournament tournament) {
        tournamentService.deleteTournament(tournament);
    }


}
