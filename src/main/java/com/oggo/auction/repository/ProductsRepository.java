package com.oggo.auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oggo.auction.model.Products;

public interface ProductsRepository extends JpaRepository<Products, String> {
	 List<Products> findByUserIdAndBidStatus(String userId, String bidStatus);
}
