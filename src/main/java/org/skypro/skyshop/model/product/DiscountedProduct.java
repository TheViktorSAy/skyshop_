package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int basePrice; // Базовая цена
    private final int discount;   // Скидка в процентах

    public DiscountedProduct(String name, int basePrice, int discount, UUID id) {
        super(name, id); // Передаем id в родительский класс
        validatePrice(basePrice);
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Процент скидки должен быть в диапазоне от 0 до 100 включительно.");
        }
        this.basePrice = basePrice;
        this.discount = discount;
    }


    @Override
    public int getPrice() {
        return basePrice - (basePrice * discount / 100); // Рассчитываем цену с учетом скидки
    }

    @Override
    public boolean isSpecial() {
        return true; // Это специальный товар
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + discount + "%)";
    }
}
