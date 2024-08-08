package com.oggo.auction.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Products {



	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "prod_idx")
	    @JsonProperty("prodIdx")
	    private Long prodIdx;

	    @Column(name = "prod_name", length = 50)
	    @JsonProperty("prodName")
	    private String prodName;

	    @Column(name = "prod_info")
	    @JsonProperty("prodInfo")
	    private String prodInfo;

	    @Column(name = "bid_price")
	    @JsonProperty("bidPrice")
	    private Long bidPrice;

	    @Column(name = "immediate_price")
	    @JsonProperty("immediatePrice")
	    private Long immediatePrice;

	    @Column(name = "bid_status", length = 1)
	    @JsonProperty("bidStatus")
	    private char bidStatus;

	    @Column(name = "created_at")
	    @JsonProperty("createdAt")
	    private Timestamp createdAt;

	    @Column(name = "end_at")
	    @JsonProperty("endAt")
	    private Timestamp endAt;

	    @Column(name = "user_id", length = 50)
	    @JsonProperty("userId")
	    private String userId;

	    @Column(name = "prod_img_path", length = 200)
	    @JsonProperty("prodImgPath")
	    private String prodImgPath;

	    @Column(name = "buyer_id", length = 45)
	    @JsonProperty("buyerId")
	    private String buyerId;

	    @Transient
	    @JsonProperty("sellerNickname")
	    private String sellerNickname;

		public Long getProdIdx() {
			return prodIdx;
		}

		public void setProdIdx(Long prodIdx) {
			this.prodIdx = prodIdx;
		}

		public String getProdName() {
			return prodName;
		}

		public void setProdName(String prodName) {
			this.prodName = prodName;
		}

		public String getProdInfo() {
			return prodInfo;
		}

		public void setProdInfo(String prodInfo) {
			this.prodInfo = prodInfo;
		}

		public Long getBidPrice() {
			return bidPrice;
		}

		public void setBidPrice(Long bidPrice) {
			this.bidPrice = bidPrice;
		}

		public Long getImmediatePrice() {
			return immediatePrice;
		}

		public void setImmediatePrice(Long immediatePrice) {
			this.immediatePrice = immediatePrice;
		}

		public char getBidStatus() {
			return bidStatus;
		}

		public void setBidStatus(char bidStatus) {
			this.bidStatus = bidStatus;
		}

		public Timestamp getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}

		public Timestamp getEndAt() {
			return endAt;
		}

		public void setEndAt(Timestamp endAt) {
			this.endAt = endAt;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getProdImgPath() {
			return prodImgPath;
		}

		public void setProdImgPath(String prodImgPath) {
			this.prodImgPath = prodImgPath;
		}

		public String getBuyerId() {
			return buyerId;
		}

		public void setBuyerId(String buyerId) {
			this.buyerId = buyerId;
		}

		public String getSellerNickname() {
			return sellerNickname;
		}

		public void setSellerNickname(String sellerNickname) {
			this.sellerNickname = sellerNickname;
		}
	

	
	// Getters and setters

}
