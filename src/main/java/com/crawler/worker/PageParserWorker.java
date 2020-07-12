package com.crawler.worker;

import com.crawler.utils.JavaScriptUtils;
import com.crawler.utils.SiteService;
import com.crawler.utils.StringUtils;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.crawler.utils.StringUtils.isEmpty;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class PageParserWorker implements Callable<List<String>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageParserWorker.class);
    private static final String SCRIPT = "script";
    private static final String SRC = "src";

    private final List<String> pageList;

    public PageParserWorker(List<String> pageList) {
        this.pageList = pageList;
    }

    @Override
    public List<String> call() {
        final List<String> jsLibraries = new LinkedList<>();
        for (String link : pageList) {
            if (isEmpty(link)) {
                return emptyList();
            }

            try {
                final Document doc = SiteService.getWebPageAsDocument(link);

                jsLibraries.addAll(doc.select(SCRIPT)
                        .stream()
                        .map(script -> script.attr(SRC))
                        .filter(StringUtils::isNotEmpty)
                        .filter(JavaScriptUtils::isPublicLibrary)
                        .collect(toList()));
            } catch (IOException ioe) {
                LOGGER.error("Not able to access the site {}", link, ioe);
            }
        }
        return jsLibraries;
    }
}
