package com.publicis.sapient.NewsSearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponse {
    private String searchKeyword;
    private String city;
    private int pageNo;
    private int totalPages;
    private long timeTakenMs;
    private List<NewsItem> articles;
    private String nextPage;
    private String prevPage;
}
