package com.publicis.sapient.NewsSearch.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.publicis.sapient.NewsSearch.util.Constants.*;

@FeignClient(name = "guardianClient", url = "${news.guardian.baseUrl}")
public interface GuardianClient {
    @GetMapping(GUARDIAN_PATH)
    public ResponseEntity<String> news(@RequestParam(QUERY) String query, @RequestParam(PAGE) int page, @RequestParam(PAGE_SIZE) int pageSize , @RequestParam(API_KEY) String apiKey);
}
