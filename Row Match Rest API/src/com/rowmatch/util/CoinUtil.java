package com.rowmatch.util;

public class CoinUtil {

    public static int getStartingCoins() {
        return 5000;
    }

    public static int getCoinsPerLevel() {
        return 25;
    }

    public static int getTournamentEntryFee() {
        return 1000;
    }

    public static int getReward(int rank, int totalPlayers) {
        int reward;
        switch (rank) {
            case 1:
                return reward = getRank1Reward();
            case 2:
                return reward = getRank2Reward();
            case 3:
                return reward = getRank3Reward();
            case 4,5,6,7,8,9,10:
                return reward = getRank4to10Reward();
            default:
                return reward = 0;
        }

    }
    public static int getRank1Reward() {
        return 10000;
    }

    public static int getRank2Reward() {
        return 5000;
    }

    public static int getRank3Reward() {
        return 3000;
    }

    public static int getRank4to10Reward() {
        return 1000;
    }

    public int calculateCoinReward(int points) {
        return 0;
    }
}
