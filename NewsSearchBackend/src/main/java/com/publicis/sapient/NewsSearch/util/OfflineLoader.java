package com.publicis.sapient.NewsSearch.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.sapient.NewsSearch.entity.NewsItem;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class OfflineLoader {
    private List<NewsItem> cachedOfflineData;
    public List<NewsItem> loadOfflineData() {
        if (cachedOfflineData == null) {
            try (InputStream inputStream = new ClassPathResource("offline-news.json").getInputStream()) {
                ObjectMapper mapper = new ObjectMapper();
                cachedOfflineData = Arrays.asList(mapper.readValue(inputStream, NewsItem[].class));
            } catch (IOException e) {
                throw new RuntimeException("Error loading offline data", e);
            }
        }
        return cachedOfflineData;
    }
}
