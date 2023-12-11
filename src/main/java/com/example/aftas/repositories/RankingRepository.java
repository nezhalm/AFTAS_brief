package com.example.aftas.repositories;

import com.example.aftas.models.Competition;
import com.example.aftas.models.MemberCompetitionKey;
import com.example.aftas.models.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RankingRepository extends JpaRepository<Ranking, MemberCompetitionKey> {
    Ranking findById_CompetitionIdAndId_MemberId(String competitionId, Integer memberId);
}
