package com.publicis.sapient.NewsSearch.service;

import com.publicis.sapient.NewsSearch.client.GuardianClientService;
import com.publicis.sapient.NewsSearch.client.NytClientService;
import com.publicis.sapient.NewsSearch.entity.NewsItem;
import com.publicis.sapient.NewsSearch.entity.NewsResponse;
import com.publicis.sapient.NewsSearch.util.OfflineLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static com.publicis.sapient.NewsSearch.util.Constants.TOTAL_PAGES;

@Service
@RequiredArgsConstructor
public class NewsService {
    @Autowired
    private GuardianClientService guardianClientService;
    @Autowired
    private NytClientService nytClient;

    public NewsResponse searchNews(String keyword, int page, int pageSize, boolean offlineMode) {
        long startTime = System.currentTimeMillis();
        List<NewsItem> aggregatedNews = new ArrayList<>();

        if (offlineMode) {
            // Load data from offline cache (local JSON file)
            aggregatedNews = loadOfflineData();
            TOTAL_PAGES = 1;
        } else {
            // Fetch data from APIs
            CompletableFuture<List<NewsItem>> guardianFuture = CompletableFuture.supplyAsync(() -> guardianClientService.fetchNews(keyword, page, pageSize));
            CompletableFuture<List<NewsItem>> nytFuture = CompletableFuture.supplyAsync(() -> nytClient.fetchNews(keyword, page, pageSize));

            try {
                aggregatedNews.addAll(guardianFuture.get());
                aggregatedNews.addAll(nytFuture.get());
            } catch (Exception e) {
                throw new RuntimeException("Error fetching news from APIs", e);
            }
        }

        // Remove duplicates
        Set<NewsItem> uniqueNews = new LinkedHashSet<>(aggregatedNews);
        System.out.println("un: " + uniqueNews);
        // Build response
        long timeTaken = System.currentTimeMillis() - startTime;
        return new NewsResponse(keyword, "Unknown City", page,  TOTAL_PAGES, timeTaken, new ArrayList<>(uniqueNews),
                "" +(page + 1), page > 1 ?  "" +(page - 1) : null);
    }

    private List<NewsItem> loadOfflineData() {
        OfflineLoader ol = new OfflineLoader();

        return ol.loadOfflineData();
    }
}

