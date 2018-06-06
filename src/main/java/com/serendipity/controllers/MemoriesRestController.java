package com.serendipity.controllers;

import com.serendipity.controllers.bodies.MemoryBody;
import com.serendipity.entities.Hotspot;
import com.serendipity.entities.Memory;
import com.serendipity.entities.SerendipityCollection;
import com.serendipity.repositories.MemoryRepository;
import com.serendipity.services.ImageService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

@RestController
public class MemoriesRestController {

    private EntityManager entityManager;
    private MemoryRepository memoryRepository;
    private ImageService imageService;

    @Autowired
    public MemoriesRestController(MemoryRepository memRepo, ImageService imageService, EntityManager entityManager) {
        memoryRepository = memRepo;
        this.imageService = imageService;
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
            @RequestBody MemoryBody args
            ) {
        Session session = entityManager.unwrap(Session.class);
        Memory m = new Memory();

        String base64Image = args.getBase64Image();
        Hotspot h = session.load(Hotspot.class, args.getHotspotId());

        m.setCurrentHotspot(h);
        m.setOriginalHotspot(h);
        m.setMessage(args.getMessage());
        m.setCreator(1);
        m.setOwner(1);

        memoryRepository.save(m);

        if (!StringUtils.isEmpty(base64Image)) {
            String imageLink = imageService.saveImageForMemory(m.getId(), base64Image);
            m.setImageLink(imageLink);
        }

        return m;
    }
}
