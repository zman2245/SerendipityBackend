package com.serendipity.controllers;

import com.serendipity.entities.Memory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MemoriesRestController {

    @RequestMapping("/memories")
    @ResponseBody
    public List<Memory> myMemories() throws URISyntaxException {
        List<Memory> memories = new ArrayList<>();
        Memory m;

        m = new Memory();
        m.setBackgroundColor(0x101020);
        m.setMessage("hello world");
        m.setReference(new URI("http://www.google.com"));
        memories.add(m);

        m = new Memory();
        m.setBackgroundColor(0x202020);
        m.setMessage("What is this?");
        m.setReference(new URI("http://www.yahoo.com"));
        memories.add(m);

        return memories;
    }
}
