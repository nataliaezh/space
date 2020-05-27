package com.hedgehogs.space.repositories;

import com.hedgehogs.space.entities.Launch;
import com.hedgehogs.space.entities.Rocket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaunchesRepository extends JpaRepository<Launch, Long> {
    Launch findOneByRocktid (String rocketid);
    boolean existsByRocktid (String rocktid);
}
