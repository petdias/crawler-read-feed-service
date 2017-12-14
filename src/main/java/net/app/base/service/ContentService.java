package net.app.base.service;

import net.app.base.model.Content;
import net.app.base.model.ImgContent;
import net.app.base.model.PContent;
import net.app.base.model.UlContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentService {

    public List<Content> getContentByHtml(String html) {
        List<Content> contents = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        doc.getAllElements().forEach(element -> {
            switch (element.tagName()) {
                case "p":
                    contents.add(new PContent(element.text()));
                    break;
                case "img":
                    contents.add(new ImgContent(element.attributes().get("src")));
                    break;
                case "ul":
                    contents.add(new UlContent(element));
                    break;
                default:
                    break;
            }
        });

        return contents;
    }
}
