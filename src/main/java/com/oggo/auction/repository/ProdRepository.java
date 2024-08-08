package com.oggo.auction.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.oggo.auction.model.Products;

@Repository
public interface ProdRepository extends JpaRepository<Products, Long> {



    @Query("SELECT p FROM Products p ORDER BY p.prodIdx DESC")
    List<Products> findAllByOrderByProdIdxDesc();

    Products findByProdIdx(int prodIdx);
    
    @Query("SELECT p FROM Products p WHERE p.buyerId = :buyerId AND p.bidStatus = 'Y'")
    List<Products> findUserBidItems(@Param("buyerId") String buyerId);

    @Query("SELECT u.nickname FROM Users u WHERE u.userId = :userId")
    String findSellerNicknameByUserId(@Param("userId") String userId);
}
