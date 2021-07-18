package com.moxo.app.service.impl;

import com.moxo.app.dto.PublisherDetails;
import com.moxo.app.entity.FeedEntity;
import com.moxo.app.repository.FeedsRepository;
import com.moxo.app.service.FeedProcessor;
import com.moxo.app.service.parser.Parser;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FeedProcessorImpl implements FeedProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(FeedProcessorImpl.class);
    private final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);
    private final Map<String, Parser> parserMap = new HashMap<>();

    private final FeedsRepository feedsRepository;
    private final List<Parser> parsers;

    @Autowired
    public FeedProcessorImpl(FeedsRepository feedsRepository, List<Parser> parsers) {
        this.feedsRepository = feedsRepository;
        this.parsers = parsers;
        for (Parser parser : parsers) {
            parserMap.put(parser.getSource(), parser);
        }
    }

    private Parser getParser(String publisher) {
        return parserMap.getOrDefault(publisher.toUpperCase(), parserMap.get("DEFAULT"));
    }

    @Override
    public void submit(PublisherDetails details) {
        EXECUTOR_SERVICE.submit(() -> parseUrl(details));
    }

    private void parseUrl(PublisherDetails details) {
        try {
            SyndFeed rssFeed = new SyndFeedInput().build(new XmlReader(new URL(details.url())));

            if (rssFeed != null) {

                var entries = rssFeed.getEntries();
                var feedEntityList = new ArrayList<FeedEntity>();
                for (SyndEntry entry : entries) {

                    Parser parser = getParser(details.publisher());
                    FeedEntity feedEntity = new FeedEntity();
                    feedEntity.setTitle(parser.getTitle(entry));
                    feedEntity.setAuthor(parser.getAuthor(entry));
                    feedEntity.setLink(parser.getLink(entry));
                    feedEntity.setPublishedAt(parser.getPublishedAt(entry));
                    feedEntity.setDescription(parser.getDescription(entry));
                    feedEntity.setImg(parser.getImage(entry));
                    feedEntity.setPublisher(parser.getPublisher(details));
                    feedEntity.setCat(parser.getCategories(entry));
                    feedEntity.setState(parser.getState());
                    feedEntity.setScore(Double.valueOf(feedEntity.getPublishedAt()));

                    feedEntityList.add(feedEntity);
                }

                feedsRepository.saveAll(feedEntityList);
            } else {
                LOGGER.info("Feed is null for " + details);
            }

        } catch (Exception e) {
            LOGGER.error("Error while parsing", e);
        }
    }
}
