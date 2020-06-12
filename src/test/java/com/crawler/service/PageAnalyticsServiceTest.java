package com.crawler.service;

import com.crawler.pojo.UrlData;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class PageAnalyticsServiceTest {

    @Autowired
    private PageAnalyticsService pageAnalyticsService;

    @Test
    public void convertUrlsToLinkDataSorted() {
        List<String> javaScriptLinks = Arrays.asList("jqury.js", "angular.js", "react.js",
                "minify.js", "angular.js", "something_else.js", "react.js", "angular.js",
                "minify.js", "everyting.js", "nothing.js", "angular.js",
                "fish.js", "wordpress.js", "oracle.js", "react.js", "blogspot.js");

        List<UrlData> urlData = pageAnalyticsService.convertUrlsToLinkDataSorted(javaScriptLinks);

        Assert.assertFalse(urlData.isEmpty());

        Assert.assertEquals("angular", urlData.get(0).getUrl());
        Assert.assertEquals(4, urlData.get(0).getNoOfOccurrences());

        Assert.assertEquals("react", urlData.get(1).getUrl());
        Assert.assertEquals(3, urlData.get(1).getNoOfOccurrences());

        Assert.assertEquals("minify", urlData.get(2).getUrl());
        Assert.assertEquals(2, urlData.get(2).getNoOfOccurrences());

        Assert.assertEquals("oracle", urlData.get(3).getUrl());
        Assert.assertEquals(1, urlData.get(3).getNoOfOccurrences());

        Assert.assertEquals("wordpress", urlData.get(4).getUrl());
        Assert.assertEquals(1, urlData.get(4).getNoOfOccurrences());

    }
}
