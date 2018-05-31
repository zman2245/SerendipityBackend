package com.serendipity.controllers;

import com.serendipity.entities.Hotspot;
import com.serendipity.entities.SerendipityCollection;
import com.serendipity.exceptions.ResourceNotFoundException;
import com.serendipity.repositories.HotspotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class HotspotsRestController {

    public static final String DEFAULT_RADIUS = "500";

    private HotspotRepository hotspotRepository;

    @Autowired
    public HotspotsRestController(HotspotRepository hotspotRepository) {
        this.hotspotRepository = hotspotRepository;
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
    public SerendipityCollection<Hotspot> getAllHotspots() {
        List<Hotspot> hotspots = hotspotRepository.findAll();

        return new SerendipityCollection<>(hotspots);
    }

    @RequestMapping(value = "/hotspots", method = RequestMethod.POST)
    @ResponseBody
    public Hotspot createHotspot(
            @RequestParam("latitude") float latitude,
            @RequestParam("longitude") float longitude,
            @RequestParam(value = "radius", required = false, defaultValue = DEFAULT_RADIUS) int radius,
            @RequestParam("creator") int creatorId) {
        Hotspot h = new Hotspot();
        h.setCreator(creatorId);
        h.setRadiusInFeet(radius);
        h.setLatitude(latitude);
        h.setLongitude(longitude);

        hotspotRepository.save(h);

        return h;
    }
}
