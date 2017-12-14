package net.app.base.model;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class UlContent extends Content {

    List<String> content = new ArrayList<>();

    public UlContent(Element element) {
        super.setType("links");
        addLinks(element);
    }

    private void addLinks(Element element){
        element.children().forEach(li -> {
            if (Objects.nonNull(li.select("a").first())) {
                this.content.add(li.select("a").first().attr("href"));
            }
        });
    }
}
