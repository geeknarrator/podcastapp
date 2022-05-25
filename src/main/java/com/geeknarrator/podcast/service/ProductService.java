package com.geeknarrator.podcast.service;

import com.geeknarrator.podcast.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductService {

    public static final Product MICROFONE = Product.builder()
            .id("GN123")
            .name("Samson q2")
            .price(new BigDecimal("549.99"))
            .discountPercentage(0)
            .build();
    public static final Product CAMERA = Product.builder()
            .id("GN1234")
            .name("Camera Full HD")
            .price(new BigDecimal("149.99"))
            .discountPercentage(2)
            .build();
    public static final Product COFFEE_MUG = Product.builder()
            .id("GN12345")
            .name("The GeekNarrator Coffee mug")
            .price(new BigDecimal("36.49"))
            .discountPercentage(5)
            .build();

    private final Map<String, Product> products = new ConcurrentHashMap<>();
    private final Random random;

    public ProductService() {
        this(System.currentTimeMillis());
    }

    protected ProductService(long randomSeed) {
        this.random = new Random(randomSeed);
        this.products.put(MICROFONE.getId(), MICROFONE);
        this.products.put(CAMERA.getId(), CAMERA);
        this.products.put(COFFEE_MUG.getId(), COFFEE_MUG);
    }

    public Optional<Product> getProductById(String productId) {
        if (!productId.startsWith("GN")) {
            return Optional.empty();
        }

        return Optional.ofNullable(products.computeIfAbsent(productId, this::sampleProductForId));

    }

    private Product sampleProductForId(String productId) {
        if (productId.startsWith("GN1")) {
            return Product.builder()
                    .id(productId)
                    .name("Sample product GN1 " + productId.substring(2))
                    .price(BigDecimal.valueOf(random.nextInt(20000) / 10.0))
                    .discountPercentage(0f)
                    .build();
        }

        if (productId.startsWith("GN2")) {
            return Product.builder()
                    .id(productId)
                    .name("Sample product GN2 " + productId.substring(2))
                    .price(BigDecimal.valueOf(random.nextInt(10000) / 10.0))
                    .discountPercentage(2)
                    .build();
        }
        if (productId.startsWith("GN3")) {
            return Product.builder()
                    .id(productId)
                    .name("Sample product GN3 " + productId.substring(2))
                    .price(BigDecimal.valueOf(random.nextInt(1000) / 10.0))
                    .discountPercentage(5)
                    .build();
        }

        return null;
    }

}
