package com.assignment.service;

import com.assignment.config.GoogleConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;


@Service
public class GoogleSearchResultService {

    private final GoogleConfig googleConfig;

    public GoogleSearchResultService(final GoogleConfig googleConfig) {
        this.googleConfig = googleConfig;
    }

    public Set<String> getLinksFromGoogle(String queryString) throws IOException {
        String request = String.format(googleConfig.getUrlPattern(), queryString, googleConfig.getResultCount());//"https://www.google.com/search?q=" + queryString + "&num=200";
        Document doc = Jsoup
                .connect(request)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")
                .timeout(5000).get();
        Elements links = doc.select("div.r > a");

        for (Element link : links) {
            System.out.println(link.attr(("href")) + "     " + link.text());
        }
        return null;
    }
}
