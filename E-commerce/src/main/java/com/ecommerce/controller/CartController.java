package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/{userId}/{productId}")
    public Cart addToCart(@PathVariable Long userId,
                          @PathVariable Long productId) {

        User user = userRepo.findById(userId).orElseThrow();

        return cartService.addToCart(user, productId);
    }

    
    @DeleteMapping("/{userId}/{productId}")
    public Cart removeFromCart(@PathVariable Long userId,
                               @PathVariable Long productId) {

        User user = userRepo.findById(userId).orElseThrow();

        return cartService.removeFromCart(user, productId);
    }
}