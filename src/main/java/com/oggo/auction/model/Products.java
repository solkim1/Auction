package com.oggo.auction.model;

import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Products")

public class Products {
	public Long getProdIdx() {
		return prodIdx;
	}

	public void setProdIdx(Long prodIdx) {
		this.prodIdx = prodIdx;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public String getProd_info() {
		return prod_info;
	}

	public void setProd_info(String prod_info) {
		this.prod_info = prod_info;
	}

	public Long getBid_price() {
		return bid_price;
	}

	public void setBid_price(Long bid_price) {
		this.bid_price = bid_price;
	}

	public Long getImmediate_price() {
		return immediate_price;
	}

	public void setImmediate_price(Long immediate_price) {
		this.immediate_price = immediate_price;
	}

	public char getBid_status() {
		return bid_status;
	}

	public void setBid_status(char bid_status) {
		this.bid_status = bid_status;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getEnd_at() {
		return end_at;
	}

	public void setEnd_at(Timestamp end_at) {
		this.end_at = end_at;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getProd_img_path() {
		return prod_img_path;
	}

	public void setProd_img_path(String prod_img_path) {
		this.prod_img_path = prod_img_path;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="prod_idx")
	private Long prodIdx;
	
	@Column(name="prod_name", length = 50)
	private String prod_name;
	
	@Column(name="prod_info")
	private String prod_info;
	
	@Column(name="bid_price")
	private Long bid_price;
	
	@Column(name="immediate_price")
	private Long immediate_price;
	
	@Column(name="bid_status", length=1)
	private char bid_status;
	
	@Column(name="created_at")
	private Timestamp created_at;
	
	@Column(name="end_at")
	private Timestamp end_at;
	
	@Column(name="user_id", length = 50)
	private String user_id;
	
	@Column(name="prod_img_path",length = 200)
	private String prod_img_path;
}
