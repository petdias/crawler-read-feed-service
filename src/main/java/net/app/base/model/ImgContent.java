package net.app.base.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImgContent extends Content {

    private String content;

    public ImgContent(String content) {
        super.setType("image");
        this.content = content;
    }
}
