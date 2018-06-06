package com.serendipity.controllers;

import com.serendipity.entities.Memory;
import com.serendipity.entities.SerendipityCollection;
import com.serendipity.repositories.MemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoriesRestController {

    private MemoryRepository memoryRepository;

    @Autowired
    public MemoriesRestController(MemoryRepository memRepo) {
        memoryRepository = memRepo;
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
    public void createMemory() {
        Memory m = new Memory();
        m.setBgColor(0x101020);
        m.setMessage("hello world");

        // TODO: for now just use hard-coded value for creator of 1
        m.setCreator(1);
        m.setOwner(null);
        m.setOriginalHotspot(null);
        m.setCurrentHotspot(null);

        memoryRepository.save(m);
    }
}
