package com.serendipity.repositories;

import com.serendipity.entities.Hotspot;
import com.vividsolutions.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotspotRepository extends JpaRepository<Hotspot, Long> {

    public static final int SRID_EARTH = 4326;

//    @Query("FROM Hotspot h WHERE ST_Covers(:area, h.whereAt) = true")
//    List<Hotspot> findWithinArea(Geometry area);

    /**
     * This seems to work fine
     *
     * @param point
     * @param radius
     * @return
     */
    @Query("FROM Hotspot h WHERE ST_DWithin(:point, h.location, :radius) = true")
    List<Hotspot> findWithinArea(Geometry point, double radius);
}
