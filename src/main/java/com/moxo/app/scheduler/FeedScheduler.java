package com.moxo.app.scheduler;

import com.moxo.app.service.FeedParser;
import org.springframework.scheduling.annotation.Scheduled;
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

    private final FeedParser feedParser;

    public FeedScheduler(FeedParser feedParser) {
        this.feedParser = feedParser;
    }

    @Scheduled(fixedDelay = 4*60*60*1000)
    @PostConstruct
    public void init() {
        for (String s : URL_SET) {
            feedParser.parseFeedUrl(s);
        }
    }
}
