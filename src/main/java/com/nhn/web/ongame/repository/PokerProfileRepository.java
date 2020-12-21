package com.nhn.web.ongame.repository;

import com.nhn.web.ongame.models.PokerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokerProfileRepository extends JpaRepository<PokerProfile,Long> {
}
