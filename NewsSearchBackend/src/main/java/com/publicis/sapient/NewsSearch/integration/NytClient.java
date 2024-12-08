package com.publicis.sapient.NewsSearch.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import static com.publicis.sapient.NewsSearch.util.Constants.*;

@FeignClient(name = "nytClient", url = "${news.nyt.baseUrl}")
public interface NytClient {
    @GetMapping(NYT_PATH)
    public ResponseEntity<String> news(@RequestParam(QUERY) String query, @RequestParam(PAGE) int page, @RequestParam(API_KEY) String apiKey);
}
