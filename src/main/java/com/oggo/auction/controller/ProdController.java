package com.oggo.auction.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oggo.auction.codec.Base64Codec;
import com.oggo.auction.model.Products;
import com.oggo.auction.service.ProdService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ProdController {

	@Autowired
	private ProdService service = new ProdService();

	
	// 상품 리스트 조회
	@PostMapping(value = "/prodCheck")
	public ResponseEntity<List<Products>> prodCheck() {

		System.out.println("프론트에서 상품리스트 데이터 요청 들어옴");

		List<Products> prodList = service.prodCheck();

		System.out.println(prodList.get(0).toString());

		if (prodList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(prodList);
		} else {

			try {
				for (int i = 0; i < prodList.size(); i++) {
					if (prodList.get(i).getProd_img_path() != null) {
						String imgByte = Base64Codec.makeStringWithFile(prodList.get(i).getProd_img_path());
						prodList.get(i).setProd_img_path(imgByte);
						System.out.println(imgByte);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return ResponseEntity.ok(prodList);
		}

	}
	
	// 상품 상세정보 조회
	@PostMapping(value="/prodDetail")
	public Products prodDetail(@RequestParam("prodIdx") int prodIdx) {
		System.out.println(prodIdx);
		Products product = service.prodDetail(prodIdx);
		System.out.println(product.getProd_name());
		return product;
	}
}
