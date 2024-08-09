package com.oggo.auction.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.oggo.auction.model.Products;

import jakarta.transaction.Transactional;

@Repository
public interface ProdRepository extends JpaRepository<Products, Long> {

	// bid_status = N ← 현재 마감이 되지않는 경매 품목만 불러오기
	@Query("SELECT p FROM Products p WHERE p.bidStatus = 'N' ORDER BY p.prodIdx DESC")
	List<Products> findAllByOrderByProdIdxDesc();

	// prodIdx로 상품정보 가져오기(상품 디테일 확인할때 사용)
	Products findByProdIdx(int prodIdx);

	
	@Query("SELECT p FROM Products p WHERE p.buyerId = :buyerId"
//			+ " AND p.bidStatus = 'Y'"
			)
	List<Products> findUserBidItems(@Param("buyerId") String buyerId);

	
	// 파는 user의 닉네임 땡겨오기
	@Query("SELECT u.nickname FROM Users u WHERE u.userId = :userId")
	String findSellerNicknameByUserId(@Param("userId") String userId);

	// 자신이 올린 상품 확인
	@Query("SELECT p FROM Products p WHERE p.userId = :userId ORDER BY p.prodIdx DESC")
	List<Products> findAllByUserIdOrderByProdIdxDesc(@Param("userId") String userId);

	
	// 입찰하는 sql - buyerId를 현재 가장높게 입찰한 user의 id로 변경과 함께 입찰가도 업데이트
	@Transactional
	@Modifying
	@Query(value = "UPDATE products SET bid_price = :money , buyer_id = :userId WHERE prod_idx = :prodIdx", nativeQuery = true)
	void bidUpdate(@Param("prodIdx") int prodIdx, @Param("money") int money, @Param("userId") String userId);

	
	// 즉시구매 - bid_status Y로 업데이트
	@Transactional
	@Modifying
	@Query(value = "UPDATE products SET bid_price = :money , bid_status = 'Y', buyer_id = :userId WHERE prod_idx = :prodIdx", nativeQuery = true)
	void buy(@Param("prodIdx") int prodIdx, @Param("money") int money, @Param("userId") String userId);
	
	
	// 1분마다 end_at의 시간을 체크하여 경매상태를 Y로 바꿈
	@Transactional
	@Modifying
    @Query("UPDATE Products p SET p.bidStatus = 'Y' WHERE p.endAt <= CURRENT_TIMESTAMP AND p.bidStatus = 'N'")
    void updateExpiredBids();

}
