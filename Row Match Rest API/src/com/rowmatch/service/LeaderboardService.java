package com.rowmatch.service;

import com.rowmatch.model.Leaderboard;
import com.rowmatch.model.Tournament;
import com.rowmatch.model.User;
import com.rowmatch.repository.LeaderboardRepository;
import com.rowmatch.repository.TournamentRepository;
import com.rowmatch.repository.UserRepository;
import com.rowmatch.util.CoinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {
    private final UserRepository userRepository;
    private final TournamentRepository tournamentRepository;
    private final LeaderboardRepository leaderboardRepository;
    private final CoinUtil coinUtil;

    @Autowired
    public LeaderboardService(UserRepository userRepository,
                              TournamentRepository tournamentRepository,
                              LeaderboardRepository leaderboardRepository,
                              CoinUtil coinUtil) {
        this.userRepository = userRepository;
        this.tournamentRepository = tournamentRepository;
        this.leaderboardRepository = leaderboardRepository;
        this.coinUtil = coinUtil;
    }


    public Leaderboard createLeaderboard(Tournament tournament, User user) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.setTournament(tournament);
        leaderboard.setUser(user);
        leaderboard.setPoints(0);
        return leaderboardRepository.save(leaderboard);
    }

    public Leaderboard updateLeaderboard(Leaderboard leaderboard, int points) {
        leaderboard.setPoints(leaderboard.getPoints() + points);
        return leaderboardRepository.save(leaderboard);
    }

    public void handleCoinRewards(Tournament tournament) {
        List<Leaderboard> leaderboards = getLeaderboards(tournament);
        for (Leaderboard leaderboard : leaderboards) {
            User user = leaderboard.getUser();
            int reward = coinUtil.calculateCoinReward(leaderboard.getPoints());
            user.setCoins(user.getCoins() + reward);
            userRepository.save(user);
        }
    }

    public List<Leaderboard> getAllLeaderboards() {
        return leaderboardRepository.findAll();
    }

    public List<Leaderboard> getLeaderboards(Tournament tournament) {
        return getLeaderboards(tournament);
    }

}

