package com.rowmatch.repository;

import com.rowmatch.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    Tournament findById(long id);

    List<Tournament> findAll();

    Tournament save(Tournament tournament);

    void delete(Tournament tournament);

    Tournament getActiveTournament();

}
