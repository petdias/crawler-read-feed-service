package net.app.base.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PContent extends Content {

    private String content;

    public PContent(String content) {
        super.setType("text");
        this.content = content;
    }
}
