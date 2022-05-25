package com.geeknarrator.podcast.model;

import java.util.List;

public class Cart {
    Long cartId;
    Long userId;
    List<Product> products;

    public Long getCartId() {
        return cartId;
    }

    public Cart setCartId(Long cartId) {
        this.cartId = cartId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Cart setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Cart setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

}
