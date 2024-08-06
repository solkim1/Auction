package com.oggo.auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oggo.auction.model.Products;
import com.oggo.auction.repository.ProductsRepository;

@RestController
public class ProductsController {
    @Autowired
    private ProductsRepository repository;

    @GetMapping(value = "/user/bid-products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Products> getUserBidProducts(@RequestParam String userId) {
        return repository.findByUserIdAndBidStatus(userId, "Y");
    }
}
