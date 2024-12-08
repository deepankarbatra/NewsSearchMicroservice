package com.publicis.sapient.NewsSearch.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.sapient.NewsSearch.entity.NewsItem;
import com.publicis.sapient.NewsSearch.integration.GuardianClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.publicis.sapient.NewsSearch.util.Constants.GUARDIAN_SOURCE;
import static com.publicis.sapient.NewsSearch.util.Constants.TOTAL_PAGES;

@Component
@RequiredArgsConstructor
public class GuardianClientService {
    @Value("${news.guardian.apiKey}")
    private String apiKey;

    @Value("${news.guardian.baseUrl}")
    private String baseUrl;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GuardianClient guardianClient;

    public List<NewsItem> fetchNews(String keyword, int page, int pageSize) {



        try {
            ResponseEntity<String> response = guardianClient.news(keyword,page,pageSize,apiKey);
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode resultsNode = rootNode.at("/response/results");
            TOTAL_PAGES = rootNode.at("/response/pages").asInt();
            if (resultsNode.isArray()) {
                List<NewsItem> newsItems = new ArrayList<>();
                for (JsonNode result : resultsNode) {
                    NewsItem newsItem = NewsItem.builder()
                            .title(result.path("webTitle").asText())
                            .url(result.path("webUrl").asText())
                            .description(result.path("fields").path("trailText").asText())
                            .source(GUARDIAN_SOURCE)
                            .build();
                    newsItems.add(newsItem);
                }
                return newsItems;
            }

            return Collections.emptyList();
        }
        catch (FeignException e) {

            if(e.status() == 429)
                return Collections.emptyList();
            else throw new RuntimeException("Error fetching news from NYT API", e);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching news from Guardian API", e);
        }
    }
}

