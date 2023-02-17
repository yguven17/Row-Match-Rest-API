package com.rowmatch.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.rowmatch.model.Leaderboard;
import com.rowmatch.model.Tournament;
import com.rowmatch.model.User;
import com.rowmatch.repository.LeaderboardRepository;
import com.rowmatch.repository.TournamentRepository;
import com.rowmatch.repository.UserRepository;
import com.rowmatch.util.CoinUtil;
import com.rowmatch.util.TimeUtil;

public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final UserRepository userRepository;
    private final LeaderboardRepository leaderboardRepository;


    public TournamentService(TournamentRepository tournamentRepository, UserRepository userRepository, LeaderboardRepository leaderboardRepository) {
        this.tournamentRepository = tournamentRepository;
        this.userRepository = userRepository;
        this.leaderboardRepository = leaderboardRepository;
    }

    public Tournament findTournamentById(long id) {
        return tournamentRepository.findById(id);
    }


    public Tournament createTournament(Tournament tournament) {
        tournament.setStartTime(LocalDateTime.from(TimeUtil.now()));
        return tournamentRepository.save(tournament);
    }

    public Tournament updateTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public void deleteTournament(Tournament tournament) {
        tournamentRepository.delete(tournament);
    }

    public boolean isTournamentExpired(Tournament tournament) {
        return TimeUtil.now().isAfter(LocalTime.from(tournament.getEndTime()));
    }


    public List<Tournament> getAllTournaments() {
        return null;
    }

    public Tournament addTournament(Tournament tournament) {
        return tournament;
    }

    public Tournament enterTournament(User user, Tournament tournament) {
        user.setTournament(tournament);
        return null;
    }

    public User claimReward(User user, Tournament tournament) {
        int rank = getRank(user, tournament);
        int reward = CoinUtil.getReward(rank, getTotalPlayers());
        user.setCoins(user.getCoins() + reward);
        userRepository.save(user);
        return user;
    }

    public Tournament getCurrentTournament() {
        return tournamentRepository.getActiveTournament();
    }


    public int getRank(User user, Tournament tournament) {
        List<Leaderboard> leaderboards = leaderboardRepository.findByUserAndTournament(user, tournament);
        int rank = 1;
        for (Leaderboard lb : leaderboards) {
            if (lb.getScore() > user.getScore()) {
                rank++;
            }
        }
        return rank;
    }

    public int getTotalPlayers() {
        int numberOfPlayers = 0;

        List<User> allUsers = UserRepository.findAll();
        numberOfPlayers = allUsers.size();

        return numberOfPlayers;
    }
}

