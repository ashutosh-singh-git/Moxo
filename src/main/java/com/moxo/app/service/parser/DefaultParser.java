package com.moxo.app.service.parser;

import com.moxo.app.util.FeedUtil;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DefaultParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultParser.class);

    @Override
    public String getDescription(SyndEntry entry) {
        String text = Parser.super.getDescription(entry);

        if (!entry.getContents().isEmpty()) {
            for (SyndContent content : entry.getContents()) {
                if (FeedUtil.HTML_TYPES.contains(content.getType())) {
                    Document doc = Jsoup.parse(content.getValue());
                    if (text.length() < doc.text().length()) {

                        LOGGER.info(doc + "\n\n");

                        if (StringUtils.hasLength(doc.select("p").text())) {
                            text = doc.select("p").text();
                        } else {
                            text = doc.text();
                        }
                    }
                }
            }
        }
        return text.substring(0, Math.min(text.length(), 250)) + "...";
    }

    @Override
    public String getImage(SyndEntry entry) {
        if (!entry.getContents().isEmpty()) {
            for (SyndContent content : entry.getContents()) {
                if (FeedUtil.HTML_TYPES.contains(content.getType())) {
                    Document doc = Jsoup.parse(content.getValue());
                    Element imageElement = doc.select("img").first();
                    if (imageElement != null) {
                        return imageElement.absUrl("src");
                    }
                }
            }
        }
        return "";
    }

    @Override
    public String getSource() {
        return "DEFAULT";
    }
}
