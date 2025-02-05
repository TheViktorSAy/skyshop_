package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BasketServiceTest {

    private ProductBasket productBasket;
    private StorageService storageService;
    private BasketService basketService;

    @BeforeEach
    public void setUp() {
        productBasket = Mockito.mock(ProductBasket.class); // Создание мока для ProductBasket
        storageService = Mockito.mock(StorageService.class); // Создание мока для StorageService
        basketService = new BasketService(productBasket, storageService); // Инициализация BasketService
    }

    @Test
    public void testAddNonExistentProductThrowsException() {
        // Создаем UUID для несуществующего продукта
        UUID nonExistentId = UUID.randomUUID();

        // Настраиваем мок, чтобы он возвращал пустое значение для несуществующего продукта
        when(storageService.getProductByIdOrThrow(nonExistentId)).thenThrow(new NoSuchProductException("Продукт с ID " + nonExistentId + " не найден."));

        // Проверяем, что при вызове addProduct выбрасывается исключение NoSuchProductException
        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(nonExistentId));
    }

    @Test
    public void testAddExistingProductCallsAddProductOnBasket() {
        // Создаем UUID для существующего продукта
        UUID existingId = UUID.randomUUID();
        Product product = mock(Product.class);

        // Настраиваем мок, чтобы он возвращал продукт при вызове getProductByIdOrThrow
        when(storageService.getProductByIdOrThrow(existingId)).thenReturn(product);

        // Вызываем метод addProduct
        basketService.addProduct(existingId);

        // Проверяем, что метод addProduct был вызван на productBasket
        verify(productBasket).addProduct(existingId);
    }

    @Test
    public void testGetUserBasketReturnsEmptyWhenBasketIsEmpty() {
        when(productBasket.getProducts()).thenReturn(Collections.emptyMap());

        UserBasket userBasket = basketService.getUserBasket();

        assertTrue(userBasket.getItems().isEmpty());
        assertEquals(0, userBasket.getTotal());
    }

    @Test
    public void testGetUserBasketReturnsCorrectItems() {
        UUID productId = UUID.randomUUID();
        Product product = mock(Product.class);
        when(product.getPrice()).thenReturn(100);
        when(storageService.getProductById(productId)).thenReturn(Optional.of(product));
        when(productBasket.getProducts()).thenReturn(Map.of(productId, 2));

        UserBasket userBasket = basketService.getUserBasket();

        assertEquals(1, userBasket.getItems().size());
        assertEquals(200, userBasket.getTotal());
    }
}