package com.rowmatch.model;

import com.rowmatch.repository.UserRepository;
import com.rowmatch.util.CoinUtil;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "level")
    private int level;

    @Column(name = "coins")
    private int coins;



    public User() {
        this.level = 1;
        this.coins = CoinUtil.getStartingCoins();
    }

    public static List<User> all() {
        UserRepository repository = new UserRepository();
        return repository.findAll();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setTournament(Tournament tournament) {

    }
    public User nextLevel(User user) {
        user.setLevel(user.getLevel() +1);
        user.setCoins(user.getCoins() +25);
        return user;
    }

    public int getScore() {
        return 0;
    }
}
