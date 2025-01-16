package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final UUID id; // Идентификатор продукта
    private final String name;

    public Product(String name, UUID id) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым или состоять только из пробелов.");
        }
        this.name = name;
        this.id = id; // Инициализация идентификатора

    }

    @Override
    public UUID getId() {
        return id; // Возвращаем идентификатор
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial(); // Новый метод

    @JsonIgnore
    public String getSearchTerm() {
        return getName(); // Возвращаем имя товара как термин поиска
    }

    @JsonIgnore
    public String getContentType() {
        return "PRODUCT"; // Тип контента
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}



