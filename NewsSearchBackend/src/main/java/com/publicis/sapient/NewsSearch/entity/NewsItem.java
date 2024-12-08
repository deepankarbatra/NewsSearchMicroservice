package com.publicis.sapient.NewsSearch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsItem {
    private String title;
    private String url;
    private String description;
    private String source;
}
