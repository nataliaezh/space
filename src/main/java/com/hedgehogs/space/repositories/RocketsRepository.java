package com.hedgehogs.space.repositories;

import com.hedgehogs.space.entities.Rocket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author natalya_ezhkova@mail.ru
 */
@Repository
public interface RocketsRepository extends JpaRepository<Rocket, Long> {
    Rocket findOneByRocketid(String rocketid);
    boolean existsByRocketid (String rocketid);
}
