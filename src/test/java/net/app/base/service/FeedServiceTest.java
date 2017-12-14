package net.app.base.service;

import net.app.base.CrawlerReadFeedApplication;
import net.app.base.model.Feed;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CrawlerReadFeedApplication.class)
@AutoConfigureMockMvc
public class FeedServiceTest {

    @Autowired
    private FeedService feedService;

    @Test
    public void getFeeds_thenReturnMagazineItem1Description13Contents() throws Exception {

        List<Feed> feeds = feedService.getFeeds("http://revistaautoesporte.globo.com/rss/ultimas/feed.xml");

        Assert.assertNotNull(feeds.size());
    }

    @Test(expected = RuntimeException.class)
    public void getFeeds_urlEmpty_ShouldReturnError() throws Exception {

        List<Feed> feeds = feedService.getFeeds("");
    }

    @Test(expected = RuntimeException.class)
    public void getFeeds_urlNULL_ShouldReturnError() throws Exception {

        List<Feed> feeds = feedService.getFeeds(null);
    }
}
