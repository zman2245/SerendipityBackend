package com.serendipity.controllers;

import com.serendipity.entities.Hotspot;
import com.serendipity.entities.SerendipityCollection;
import com.serendipity.exceptions.ResourceNotFoundException;
import com.serendipity.repositories.HotspotRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.util.GeometricShapeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 4326 corresponds to earth
 */
@RestController
public class HotspotsRestController {

    public static final String DEFAULT_RADIUS_IN_METERS = "500";

    private HotspotRepository hotspotRepository;
    private GeometryFactory geometryFactory;

    @Autowired
    public HotspotsRestController(HotspotRepository hotspotRepository) {
        this.hotspotRepository = hotspotRepository;
        this.geometryFactory = new GeometryFactory();
    }

    @RequestMapping(value = "/hotspots/nearby", method = RequestMethod.GET)
    @ResponseBody
    public SerendipityCollection<Hotspot> nearbyHotspots(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("radius") double radius) {
        SerendipityCollection<Hotspot> hotspots = new SerendipityCollection<>(new ArrayList<>());

        return hotspots;
    }

    @RequestMapping(value = "/hotspots/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Hotspot getHotspot(
            @PathVariable(value="id") String idString) {
        long id;

        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            throw new ResourceNotFoundException();
        }

        Optional<Hotspot> h = hotspotRepository.findById(id);

        if (!h.isPresent()) {
            throw new ResourceNotFoundException();
        }

        return h.get();
    }

    @RequestMapping(value = "/hotspots", method = RequestMethod.GET)
    @ResponseBody
    public SerendipityCollection<Hotspot> getHotspots(
            @RequestParam(value = "latitude", required = false) Float latitude,
            @RequestParam(value = "longitude", required = false) Float longitude,
            @RequestParam(value = "radius", required = false, defaultValue = DEFAULT_RADIUS_IN_METERS) Integer radius) {

        List<Hotspot> hotspots;

        if (latitude == null || longitude == null || radius == null) {
            // default behavior is return all (for now). May change as dataset grows.
            hotspots = hotspotRepository.findAll();
        } else {
            GeometricShapeFactory shape = new GeometricShapeFactory(geometryFactory);
            Point p = geometryFactory.createPoint(new Coordinate(longitude, latitude));
            p.setSRID(HotspotRepository.SRID_EARTH);

            hotspots = hotspotRepository.findWithinArea(p, radius);
        }

        return new SerendipityCollection<>(hotspots);
    }

    @RequestMapping(value = "/hotspots", method = RequestMethod.POST)
    @ResponseBody
    public Hotspot createHotspot(
            @RequestParam("latitude") float latitude,
            @RequestParam("longitude") float longitude,
            @RequestParam(value = "radius", required = false, defaultValue = DEFAULT_RADIUS_IN_METERS) int radius,
            @RequestParam("creator") int creatorId) {
        Hotspot h = new Hotspot();
        h.setCreator(creatorId);
        h.setRadiusInFeet(radius);
        h.setLocation(geometryFactory.createPoint(new Coordinate(longitude, latitude)));

        hotspotRepository.save(h);

        return h;
    }
}
