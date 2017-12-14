package net.app.base.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Item {

    private String title;

    private String link;

    private List<Content> description;

    public Item(String title, String link) {
        this.title = title;
        this.link = link;
    }
}
