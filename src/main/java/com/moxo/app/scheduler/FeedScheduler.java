package com.moxo.app.scheduler;

import com.moxo.app.service.FeedParser;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class FeedScheduler {

    private static final Set<String> URL_SET = Set.of(
            "https://www.motorcyclistonline.com/arcio/rss/",
            "https://www.motorcyclecruiser.com/arcio/rss/",
            "https://www.cycleworld.com/arcio/rss/",
            "https://www.visordown.com/articles/rss/",
            "https://www.indianrides.com/motorcycle-tour-blog/feed/"
            );

    private FeedParser feedParser;

    public FeedScheduler(FeedParser feedParser) {
        this.feedParser = feedParser;
    }

//    @PostConstruct
    void init() {
        for (String s : URL_SET) {
            feedParser.parseFeedUrl(s);
        }
    }
}
