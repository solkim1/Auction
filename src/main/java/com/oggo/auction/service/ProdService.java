package com.oggo.auction.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oggo.auction.model.Products;
import com.oggo.auction.repository.ProdRepository;

@Service
public class ProdService {

    @Autowired
    private ProdRepository repository;

    public List<Products> prodCheck() {
        return repository.findAllByOrderByProdIdxDesc();
    }

    public List<Products> findUserBidItems(String buyerId) {
        List<Object[]> results = repository.findUserBidItemsWithSellerNickname(buyerId);
        return results.stream()
                .map(result -> {
                    Products product = (Products) result[0];
                    String sellerNickname = (String) result[1];
                    product.setSellerNickname(sellerNickname);
                    return product;
                })
                .collect(Collectors.toList());
    }
}
