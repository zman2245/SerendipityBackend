package com.serendipity.repositories;

import com.serendipity.entities.Hotspot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotspotRepository extends JpaRepository<Hotspot, Long> {

}
