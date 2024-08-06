package com.oggo.auction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_idx", nullable = false)
    private Long prodIdx;

    @Column(name = "prod_name", length = 50, nullable = false)
    private String prodName;

    @Column(name = "prod_info", length = 255)
    private String prodInfo;

    @Column(name = "bid_price")
    private Integer bidPrice;

    @Column(name = "immediate_price")
    private Integer immediatePrice;

    @Column(name = "bid_status", length = 1)
    private char bidStatus;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "end_at")
    private String endAt;

    @Column(name = "user_id")
    private String userId;

    // Getters and Setters
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

    public Integer getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Integer bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Integer getImmediatePrice() {
        return immediatePrice;
    }

    public void setImmediatePrice(Integer immediatePrice) {
        this.immediatePrice = immediatePrice;
    }

    public char getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(char bidStatus) {
        this.bidStatus = bidStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
