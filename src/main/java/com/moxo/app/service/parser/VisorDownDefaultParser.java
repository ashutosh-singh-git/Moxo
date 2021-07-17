package com.moxo.app.service.parser;

import com.moxo.app.util.MoxoUtil;
import com.rometools.rome.feed.synd.SyndEntry;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class VisorDownDefaultParser implements Parser {

    @Override
    public String getSource() {
        return "VISORDOWN";
    }

    @Override
    public String getDescription(SyndEntry entry) {
        String text = "";
        if (entry.getDescription() != null) {
            if (MoxoUtil.HTML_TYPES.contains(entry.getDescription().getType())) {
                Document doc = Jsoup.parse(entry.getDescription().getValue());
                text = doc.text();
            } else {
                text = entry.getDescription().getValue();
            }
        }
        return text.substring(0, Math.min(text.length(), 250)) + "...";
    }

    @Override
    public String getImage(SyndEntry entry) {
        if (entry.getDescription() != null) {
            if (MoxoUtil.HTML_TYPES.contains(entry.getDescription().getType())) {
                Document doc = Jsoup.parse(entry.getDescription().getValue());
                Element imageElement = doc.select("img").first();
                if (imageElement != null) {
                    return imageElement.absUrl("src");
                }
            }
        }
        return "";
    }
}
