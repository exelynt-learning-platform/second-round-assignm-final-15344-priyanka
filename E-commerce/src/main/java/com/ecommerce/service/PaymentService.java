package com.ecommerce.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;

@Service
public class PaymentService {

    public String pay(double amount) throws Exception {

    	String secretKey = System.getenv("STRIPE_SECRET_KEY");
        Map<String, Object> params = new HashMap<>();
        params.put("amount", (int)(amount * 100));
        params.put("currency", "usd");

        PaymentIntent intent = PaymentIntent.create(params);

        return intent.getId();
    }
}