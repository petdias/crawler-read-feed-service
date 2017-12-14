package net.app.base.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
public class Feed {

    private Item item;

    public Feed(Item item) {
        this.item = item;
    }
}
