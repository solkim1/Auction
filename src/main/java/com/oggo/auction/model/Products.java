package com.oggo.auction.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_idx")
    private Long prodIdx;

    @Column(name = "prod_name", length = 50)
    private String prodName;

    @Column(name = "prod_info")
    private String prodInfo;

    @Column(name = "bid_price")
    private Long bidPrice;

    @Column(name = "immediate_price")
    private Long immediatePrice;

    @Column(name = "bid_status", length = 1)
    private char bidStatus;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "end_at")
    private Timestamp endAt;

    @Column(name = "user_id", length = 50)
    private String userId;

    @Column(name = "prod_img_path", length = 200)
    private String prodImgPath;

    @Column(name = "buyer_id", length = 45)
    private String buyerId;

    @Transient
    private String sellerNickname;

    // Getters and setters

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
}
