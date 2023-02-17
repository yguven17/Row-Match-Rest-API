package com.rowmatch.tests;

import com.rowmatch.model.Leaderboard;
import com.rowmatch.model.Tournament;
import com.rowmatch.model.User;
import com.rowmatch.repository.LeaderboardRepository;
import com.rowmatch.service.TournamentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TournamentTest {

    @Autowired
    private TournamentService tournamentService;

    @Test
    public void testEnterTournament() {
        try{
            Tournament tournament = tournamentService.enterTournament(new User(),new Tournament());
            assertEquals(tournament.getStartTime().toString(), "00:00:00");
            assertEquals(tournament.getEndTime().toString(), "20:00:00");
        } catch (Exception e){
            System.out.println("error");
        }

    }

    @Test
    public void testClaimReward() {
        try{
            User user = tournamentService.claimReward(new User(), new Tournament());
            assertEquals(user.getCoins(), 6000);
        } catch (Exception e){
            System.out.println("error");
        }

    }

    @Test
    public void testGetRankRequest() {
        try{
            int rank = tournamentService.getRank(new User(), new Tournament());
            assertEquals(rank,1 );
        } catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    public void testGetLeaderboardRequest(){
        try {
            LeaderboardRepository leaderboardRepository = new LeaderboardRepository();
            List<Leaderboard> leaderboards = leaderboardRepository.findAll();

            assertFalse(leaderboards.isEmpty());

            for (Leaderboard leaderboard : leaderboards) {
                assertTrue(leaderboard instanceof Leaderboard);
            }
        } catch (Exception e){

            System.out.println("error");
        }
    }
}
