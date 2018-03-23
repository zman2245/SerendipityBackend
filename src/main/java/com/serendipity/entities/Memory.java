package com.serendipity.entities;

import java.net.URI;

public class Memory {

    private String message;
    private URI reference;
    private int backgroundColor;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public URI getReference() {
        return reference;
    }

    public void setReference(URI reference) {
        this.reference = reference;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
