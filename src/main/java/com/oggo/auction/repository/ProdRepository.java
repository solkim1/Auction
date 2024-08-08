package com.oggo.auction.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.oggo.auction.model.Products;

@Repository
public interface ProdRepository extends JpaRepository<Products, Long> {

	@Query("SELECT p FROM Products p WHERE p.buyerId = :buyerId AND p.bidStatus = 'Y'")
	List<Products> findUserBidItems(@Param("buyerId") String buyerId);

	@Query(value="SELECT * FROM products ORDER BY prod_idx DESC",nativeQuery = true)
	List<Products> findAllByOrderByProdIdxDesc();

	@Query("SELECT p, u.nickname FROM Products p JOIN Users u ON p.userId = u.userId WHERE p.buyerId = :buyerId AND p.bidStatus = 'Y'")
	List<Object[]> findUserBidItemsWithSellerNickname(@Param("buyerId") String buyerId);

	Products findByProdIdx(int prodidx);
}
