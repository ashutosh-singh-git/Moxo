package com.moxo.app.scheduler;

import com.moxo.app.dto.PublisherDetails;
import com.moxo.app.service.ConfigService;
import com.moxo.app.service.FeedProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    private final FeedProcessor feedProcessor;
    private final ConfigService configService;

    @Autowired
    public FeedScheduler(FeedProcessor feedProcessor, ConfigService configService) {
        this.feedProcessor = feedProcessor;
        this.configService = configService;
    }

    //@Scheduled(cron = "0 0 */4 * * *")
    public void scheduleParser() {
        if(configService.getUrlParserConfig() != null) {
            List<PublisherDetails> publisherList = configService.getUrlParserConfig().getPublisherList();

            for (PublisherDetails publisherDetails : publisherList) {
                feedProcessor.submit(publisherDetails);
            }
        }
    }
}
