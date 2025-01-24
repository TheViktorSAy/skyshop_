package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products = new HashMap<>();
    private final Map<UUID, Article> articles = new HashMap<>();

    public StorageService() {
        initializeStorage();
    }

    private void initializeStorage() {
        // Добавьте тестовые данные с использованием UUID.randomUUID()
        products.put(UUID.randomUUID(), new SimpleProduct("Продукт 1", 100, UUID.randomUUID()));
        products.put(UUID.randomUUID(), new FixPriceProduct("Продукт 2", UUID.randomUUID()));
        products.put(UUID.randomUUID(), new DiscountedProduct("Продукт 3", 300, 20, UUID.randomUUID()));

        articles.put(UUID.randomUUID(), new Article("Статья 1", "Текст статьи 1", UUID.randomUUID()));
        articles.put(UUID.randomUUID(), new Article("Статья 2", "Текст статьи 2", UUID.randomUUID()));
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    public Collection<Searchable> getAllSearchables() {
        // Объединяем все searchable объекты
        List<Searchable> allSearchables = new ArrayList<>();
        allSearchables.addAll(products.values());
        allSearchables.addAll(articles.values());
        return allSearchables;
    }
}