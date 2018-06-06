package com.serendipity.controllers;

import com.serendipity.entities.Hotspot;
import com.serendipity.entities.Memory;
import com.serendipity.entities.SerendipityCollection;
import com.serendipity.repositories.MemoryRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

@RestController
public class MemoriesRestController {

    private EntityManager entityManager;
    private MemoryRepository memoryRepository;

    @Autowired
    public MemoriesRestController(MemoryRepository memRepo, EntityManager entityManager) {
        memoryRepository = memRepo;
        this.entityManager = entityManager;
    }

    @RequestMapping(value = "/memories", method = RequestMethod.GET)
    @ResponseBody
    public SerendipityCollection<Memory> myMemories() {
        // TODO: this will change to current user's memories, not hard-coded userId
        int userId = 1;

        return new SerendipityCollection<>(memoryRepository.findWByUserId(userId));
    }

    @RequestMapping(value = "/memories", method = RequestMethod.POST)
    @ResponseBody
    public Memory createMemory(
            @RequestParam(value = "hotspotId") long hotspotId,
            @RequestParam(value = "message") String message
    ) {
        Session session = entityManager.unwrap(Session.class);
        Memory m = new Memory();

        Hotspot h = session.load(Hotspot.class, hotspotId);

        m.setCurrentHotspot(h);
        m.setOriginalHotspot(h);
        m.setMessage(message);
        m.setCreator(1);
        m.setOwner(1);

        memoryRepository.save(m);

        return m;
    }
}
