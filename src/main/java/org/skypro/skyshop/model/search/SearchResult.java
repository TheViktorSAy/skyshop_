package org.skypro.skyshop.model.search;

import java.util.UUID;

public class SearchResult {
    private final UUID id; // Идентификатор
    private final String name; // Имя
    private final String contentType; // Тип контента


    public SearchResult(UUID id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    // Статический метод для создания SearchResult из Searchable
    public static SearchResult fromSearchable(Searchable searchable) {
        return new SearchResult(searchable.getId(), searchable.getName(), searchable.getContentType());
    }
}
