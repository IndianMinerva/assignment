package com.crawler.service;

import java.util.List;

public interface SearchService {
    List<String> getLinksFromSearch(String queryString);
}
