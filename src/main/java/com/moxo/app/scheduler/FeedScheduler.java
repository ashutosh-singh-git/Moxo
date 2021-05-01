package com.moxo.app.scheduler;

import com.moxo.app.dto.PublisherDetails;
import com.moxo.app.service.ConfigService;
import com.moxo.app.service.FeedParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Component
public class FeedScheduler {

    private static final Set<String> URL_SET = Set.of(
//            "https://www.motorcyclistonline.com/arcio/rss/",
//            "https://www.motorcyclecruiser.com/arcio/rss/",
//            "https://www.cycleworld.com/arcio/rss/",
            "https://www.visordown.com/articles/rss/"
//            "https://www.indianrides.com/motorcycle-tour-blog/feed/"
            );

    private final FeedParser feedParser;
    private final ConfigService configService;

    @Autowired
    public FeedScheduler(FeedParser feedParser, ConfigService configService) {
        this.feedParser = feedParser;
        this.configService = configService;
    }

    @Scheduled(fixedDelay = 4*60*60*1000)
    @PostConstruct
    public void scheduleParser() {
        if(configService.getUrlParserConfig() != null) {
            List<PublisherDetails> publisherList = configService.getUrlParserConfig().getPublisherList();

            for (PublisherDetails publisherDetails : publisherList) {
                feedParser.submit(publisherDetails);
            }
        }
    }
}
