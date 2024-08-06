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
@Data
public class Products {
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
}
