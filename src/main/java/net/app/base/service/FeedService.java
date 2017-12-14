package net.app.base.service;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import net.app.base.model.Feed;
import net.app.base.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeedService {

    @Autowired
    private ContentService contentService;

    public List<Feed> getFeeds(String url) {
        List<Feed> feeds = new ArrayList<>();
        try {
            SyndFeed syndFeed = new SyndFeedInput().build(new XmlReader(new URL(url)));
            syndFeed.getEntries().forEach(entry -> {
                Item item = new Item(entry.getTitle(), entry.getLink());
                item.setDescription(contentService.getContentByHtml(entry.getDescription().getValue()));
                feeds.add(new Feed(item));
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return feeds;
    }
}
