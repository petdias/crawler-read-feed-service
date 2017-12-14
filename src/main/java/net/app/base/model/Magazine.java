package net.app.base.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Magazine {

    private List<Feed> feed;
}
