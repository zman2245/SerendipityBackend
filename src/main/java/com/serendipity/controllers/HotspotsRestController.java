package com.serendipity.controllers;

import com.serendipity.entities.Memory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HotspotsRestController {

    @RequestMapping("/hotspots/nearby")
    @ResponseBody
    public List<Memory> nearbyHotspots(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("radius") double radius) throws URISyntaxException {
        List<Memory> memories = new ArrayList<>();

        return memories;
    }
}
