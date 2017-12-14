package net.app.base.controller;

import net.app.base.model.Magazine;
import net.app.base.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("magazines")
public class MagazineController {

    private final String url = "http://revistaautoesporte.globo.com/rss/ultimas/feed.xml";

    @Autowired
    private FeedService feedService;

    @RequestMapping(value = "feeds")
    public Magazine getFeedsByUrl() {
        Magazine magazine = new Magazine();
        magazine.setFeed(feedService.getFeeds(url));

        return magazine;
    }
}
