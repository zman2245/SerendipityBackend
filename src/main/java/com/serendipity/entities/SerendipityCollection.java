package com.serendipity.entities;

import java.util.List;

public class SerendipityCollection<T> {
    private List<T> items;
    private int count;

    public SerendipityCollection(List<T> items) {
        this.items = items;
        this.count = items.size();
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
