package com.oggo.auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oggo.auction.model.Products;
import com.oggo.auction.service.ProdService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ProdController {
	
	@Autowired
	ProdService service = new ProdService();
	
	@PostMapping(value="/prodCheck")
	public List<Products> prodCheck() {
		
		System.out.println("프론트에서 상품리스트 데이터 요청 들어옴");
		
		List<Products> prodList = service.prodCheck();
		
		return prodList;
	}
}
