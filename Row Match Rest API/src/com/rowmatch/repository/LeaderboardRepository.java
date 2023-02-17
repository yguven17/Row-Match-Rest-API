package com.rowmatch.repository;

import com.rowmatch.model.Leaderboard;
import com.rowmatch.model.Tournament;
import com.rowmatch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
    List<Leaderboard> findByTournament(Tournament tournament);

    Leaderboard save(Leaderboard leaderboard);

    List<Leaderboard> findByUserAndTournament(User user, Tournament tournament);
}
