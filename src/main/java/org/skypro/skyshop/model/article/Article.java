package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final String title;
    private final String text;
    private final UUID id; // Идентификатор статьи

    public Article(String title, String text, UUID id) {
        this.title = title;
        this.text = text;
        this.id = (id != null) ? id : UUID.randomUUID(); // Инициализация идентификатора
    }

    @Override
    public UUID getId() {
        return id;
    }

    @JsonIgnore
    public String getSearchTerm() {
        return title + text; // Возвращаем название и текст статьи как термин поиска
    }


    @JsonIgnore
    public String getContentType() {
        return "ARTICLE"; // Тип контента
    }


    @Override
    public String getName() {
        return title; // Имя Searchable объекта
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title) &&
                Objects.equals(text, article.text) &&
                Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text);
    }

    @Override
    public String toString() {
        return title + " " + text;
    }


}
