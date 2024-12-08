package com.publicis.sapient.NewsSearch.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.sapient.NewsSearch.entity.NewsItem;
import com.publicis.sapient.NewsSearch.integration.NytClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.publicis.sapient.NewsSearch.util.Constants.NYT_SOURCE;

@Component
@RequiredArgsConstructor
public class NytClientService {
    @Value("${news.nyt.apiKey}")
    private String apiKey;

    @Autowired
    private NytClient nytClient;

    @Autowired
    private ObjectMapper objectMapper;
    public List<NewsItem> fetchNews(String keyword, int page, int pageSize) {

        try {
            ResponseEntity<String> response = nytClient.news(keyword,page,apiKey);
            // Parse JSON to extract articles
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode docsNode = rootNode.at("/response/docs");
            System.out.println("docsNode " + docsNode);

            if (docsNode.isArray()) {
                List<NewsItem> newsItems = new ArrayList<>();
                for (JsonNode doc : docsNode) {
                    NewsItem newsItem = NewsItem.builder()
                            .title(doc.path("headline").path("main").asText())
                            .url(doc.path("web_url").asText())
                            .description(doc.path("abstract").asText())
                            .source(NYT_SOURCE)
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
            throw new RuntimeException("Error fetching news from NYT API", e);
        }
    }
}

