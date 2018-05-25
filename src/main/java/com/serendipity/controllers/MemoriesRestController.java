package com.serendipity.controllers;

import com.serendipity.entities.Memory;
import com.serendipity.repositories.MemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemoriesRestController {

    private MemoryRepository memoryRepository;

    @Autowired
    public MemoriesRestController(MemoryRepository memRepo) {
        memoryRepository = memRepo;
    }

    @RequestMapping(value = "/memories", method = RequestMethod.GET)
    @ResponseBody
    public List<Memory> myMemories() {
//        List<Memory> memories = new ArrayList<>();
//        Memory m;
//
//        m = new Memory();
//        m.setBgColor(0x101020);
//        m.setMessage("hello world");
//        memories.add(m);
//
//        m = new Memory();
//        m.setBgColor(0x202020);
//        m.setMessage("What is this?");
//        memories.add(m);

        return memoryRepository.findAll();
    }

    @RequestMapping(value = "/memories", method = RequestMethod.POST)
    @ResponseBody
    public void createMemory() {
        Memory m = new Memory();
        m.setBgColor(0x101020);
        m.setMessage("hello world");

        memoryRepository.save(m);
    }
}
