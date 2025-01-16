package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIXED_PRICE = 200; // Пример фиксированной цены

    public FixPriceProduct(String name, UUID id) {
        super(name, id); // Передаем id в родительский класс
    }

    @Override
    public int getPrice() {
        return FIXED_PRICE; // Возвращаем фиксированную цену
    }

    @Override
    public boolean isSpecial() {
        return true; // Это специальный товар
    }

    @Override
    public String toString() {
        return getName() + ": Фиксированная цена " + FIXED_PRICE;
    }
}
