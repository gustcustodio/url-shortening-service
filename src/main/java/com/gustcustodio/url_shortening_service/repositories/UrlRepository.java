package com.gustcustodio.url_shortening_service.repositories;

import com.gustcustodio.url_shortening_service.entities.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

    boolean existsByShortCode(String shortCode);

}
