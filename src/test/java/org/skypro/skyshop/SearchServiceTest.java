package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SearchServiceTest {

    private StorageService storageService;
    private SearchService searchService;

    @BeforeEach
    public void setUp() {
        storageService = mock(StorageService.class);
        searchService = new SearchService(storageService);
    }

    @Test
    public void testSearchWhenStorageIsEmpty() {
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        Collection<SearchResult> results = searchService.search("тест");

        assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchWhenNoMatchingObjects() {
        Article article = new Article("Article 1", "Какой-то текст", UUID.randomUUID());
        when(storageService.getAllSearchables()).thenReturn(Collections.singletonList(article));

        Collection<SearchResult> results = searchService.search("не существует");

        assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchWhenMatchingObjectExists() {
        Article article = new Article("Article 1", "Какой-то текст", UUID.randomUUID());
        when(storageService.getAllSearchables()).thenReturn(Collections.singletonList(article));

        Collection<SearchResult> results = searchService.search("Article");

        assertEquals(1, results.size());
        assertEquals(article.getId(), results.iterator().next().getId());
    }
}
