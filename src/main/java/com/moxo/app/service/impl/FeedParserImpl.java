package com.moxo.app.service.impl;

import com.moxo.app.entity.FeedEntity;
import com.moxo.app.repository.FeedsRepository;
import com.moxo.app.service.FeedParser;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FeedParserImpl implements FeedParser {

    private final static Logger LOGGER = LoggerFactory.getLogger(FeedParserImpl.class);

    private final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);

    private final FeedsRepository feedsRepository;

    public FeedParserImpl(FeedsRepository feedsRepository) {
        this.feedsRepository = feedsRepository;
    }

    @Override
    public void submit(String url) {
        EXECUTOR_SERVICE.submit(() -> parseUrl(url));
    }

    private void parseUrl(String url) {
        try {
            SyndFeed rssFeed = new SyndFeedInput().build(new XmlReader(new URL(url)));

            if (rssFeed != null) {

                var entries = rssFeed.getEntries();
                var feedEntityList = new ArrayList<FeedEntity>();
                for (SyndEntry entry : entries) {

                    FeedEntity feedEntity = new FeedEntity();
                    feedEntity.setTitle(entry.getTitle());
                    feedEntity.setAuthor(entry.getAuthor());

                    if (entry.getDescription() != null) {
                        feedEntity.setDescription(entry.getDescription().getValue());
                    }

                    if (!entry.getContents().isEmpty()) {
                        for (SyndContent content : entry.getContents()) {
                            if (content.getType().equalsIgnoreCase("html")) {
                                Document doc = Jsoup.parse(content.getValue());
                                if (feedEntity.getDescription().length() < doc.text().length()) {

                                    LOGGER.info(doc + "\n\n");

                                    if (StringUtils.hasLength(doc.select("p").text())) {
                                        String text = doc.select("p").text();
                                        feedEntity.setDescription(text.substring(0, Math.min(text.length(), 250)) + "...");
                                    } else {
                                        String text = doc.text();
                                        feedEntity.setDescription(text.substring(0, Math.min(text.length(), 250)) + "...");
                                    }
                                }

                                if (!StringUtils.hasLength(feedEntity.getImg())) {
                                    Element imageElement = doc.select("img").first();
                                    if (imageElement != null) {
                                        feedEntity.setImg(imageElement.absUrl("src"));
                                    }
                                }
                            }
                        }
                    }
                    feedEntity.setLink(entry.getLink());
                    if (entry.getPublishedDate() != null) {
                        feedEntity.setPublishedAt(entry.getPublishedDate().getTime());
                    }
                    feedEntity.setState(true);
                    feedEntity.setScore(Double.valueOf(feedEntity.getPublishedAt()));
                    feedEntityList.add(feedEntity);
                }

                feedsRepository.saveAll(feedEntityList);
            } else {
                LOGGER.info("Feed is null for " + url);
            }

        } catch (Exception e) {
            LOGGER.error("Error while parsing", e);
        }
    }
}
