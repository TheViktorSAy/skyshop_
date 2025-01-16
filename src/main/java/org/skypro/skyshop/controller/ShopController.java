package org.skypro.skyshop.controller;

import org.skypro.skyshop.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.Collection;

@RestController
public class ShopController {
    private SearchService searchService;

    @Autowired
    public void ShopController(SearchService searchService) {
        this.searchService = searchService;
    }



    @GetMapping("/search")
    public ResponseEntity<Collection<SearchResult>> search(@RequestParam String pattern) {
        Collection<SearchResult> results = searchService.search(pattern);

        if (results.isEmpty()) {
            return ResponseEntity.noContent().build(); // Возвращает статус 204 No Content, если нет результатов
        }

        return ResponseEntity.ok(results); // Возвращает статус 200 OK с результатами
    }
}


