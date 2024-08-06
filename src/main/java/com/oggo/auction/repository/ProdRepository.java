package com.oggo.auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oggo.auction.model.Products;

@Repository
public interface ProdRepository  extends JpaRepository<Products, Long> {
	
	@Query("SELECT p FROM Products p ORDER BY p.prodIdx DESC")
	List<Products> findAllByOrderByProdIdxDesc();
}
