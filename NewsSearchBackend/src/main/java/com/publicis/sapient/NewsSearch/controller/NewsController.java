package com.publicis.sapient.NewsSearch.controller;

import com.publicis.sapient.NewsSearch.entity.NewsResponse;
import com.publicis.sapient.NewsSearch.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/search")
    public ResponseEntity<NewsResponse> searchNews(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "false") boolean offlineMode) {
        NewsResponse response = newsService.searchNews(keyword, page, pageSize, offlineMode);
        return ResponseEntity.ok(response);
    }
}

