package com.oggo.auction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oggo.auction.model.Products;
import com.oggo.auction.repository.ProdRepository;

@Service
public class ProdService {
	@Autowired
	ProdRepository repository;
	
	public List<Products> prodCheck(){
		
		List<Products> prodList =repository.findAllByOrderByProdIdxDesc();
		
		return prodList;
	}
	public Products prodDetail(int prodIdx) {
		
		Products prodInfo = repository.findByProdIdx(prodIdx);
		
		return prodInfo;
	}
}
