package com.example.aftas.repositories;
import com.example.aftas.models.Competition;
import com.example.aftas.models.Fish;
import com.example.aftas.models.Hunting;
import com.example.aftas.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface HuntingRepository  extends JpaRepository<Hunting, Integer> {
    @Query("SELECT h FROM Hunting h " +
            "WHERE h.fish = :fish " +
            "AND h.member = :member " +
            "AND h.competition = :competition")
    Hunting findByFishAndMemberAndCompetition(
            @Param("fish") Fish fish,
            @Param("member") Member member,
            @Param("competition") Competition competition);
}
