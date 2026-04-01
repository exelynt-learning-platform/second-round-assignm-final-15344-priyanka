package com.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    
    public Cart getOrCreateCart(User user) {
        return cartRepo.findByUser(user)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepo.save(cart);
                });
    }

   
    public Cart addToCart(User user, Long productId) {

        Cart cart = getOrCreateCart(user);

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        
        Optional<CartItem> existingItem = cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
          
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + 1);
        } else {
            
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(1);
            newItem.setCart(cart);

            cart.getItems().add(newItem);
        }

        return cartRepo.save(cart);
    }

    
    public Cart removeFromCart(User user, Long productId) {

        Cart cart = getOrCreateCart(user);

        cart.getItems().removeIf(item ->
                item.getProduct().getId().equals(productId)
        );

        return cartRepo.save(cart);
    }
}