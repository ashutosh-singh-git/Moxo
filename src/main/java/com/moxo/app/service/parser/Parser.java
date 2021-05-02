package com.moxo.app.service.parser;

import com.moxo.app.dto.PublisherDetails;
import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndEntry;

import java.util.List;

public interface Parser {

    String getSource();

    public default String getDescription(SyndEntry entry) {
        String text = "";
        if(entry.getDescription() != null){
            text = entry.getDescription().getValue();
        }
        return text;
    };

    public default String getImage(SyndEntry entry) {
        return "";
    }

    public default long getPublishedAt(SyndEntry entry) {
        if (entry.getPublishedDate() != null) {
            return entry.getPublishedDate().getTime();
        }
        return System.currentTimeMillis();
    }

    public default String getTitle(SyndEntry entry) {
        return entry.getTitle();
    }

    public default String getAuthor(SyndEntry entry) {
        return entry.getAuthor();
    }

    public default String getLink(SyndEntry entry) {
        return entry.getLink();
    }

    public default String getPublisher(PublisherDetails publisherDetails) {
        return publisherDetails.getPublisher();
    }

    public default List<String> getCategories(SyndEntry entry) {
        return entry.getCategories().stream().map(SyndCategory::getName).toList();
    }

    public default boolean getState() {
        return true;
    }
}
