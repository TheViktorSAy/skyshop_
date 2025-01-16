package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(String name, int price, UUID id) {
        super(name, id); // Передаем id в родительский класс

        if (price <= 0) {
            throw new IllegalArgumentException("Цена продукта должна быть больше 0.");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price; // Реализация абстрактного метода
    }

    @Override
    public boolean isSpecial() {
        return false; // Это специальный товар
    }

    @Override
    public String toString() {
        return super.getName() + ": " + getPrice();
    }


}
