package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    UUID getId(); // Метод для получения идентификатора

    String getName(); // Метод для получения имени

    String getContentType(); // Метод для получения типа контента

    String getSearchTerm(); // Метод для получения термина поиска
}

