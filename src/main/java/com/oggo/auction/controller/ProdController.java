package com.oggo.auction.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oggo.auction.codec.Base64Codec;
import com.oggo.auction.model.Products;
import com.oggo.auction.service.ProdService;
import com.oggo.auction.utils.JwtUtil;

@RestController
@RequestMapping("/products")
public class ProdController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private ProdService service;

	private final String BASE_IMAGE_PATH = "src/main/resources/static/images/";

	private final String IMAGE_URL_PREFIX = "/auction/images/";

	@PostMapping(value = "/prodCheck")
	public ResponseEntity<List<Products>> prodCheck() {

		List<Products> prodList = service.prodCheck();

		if (prodList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(prodList);
		} else {
			try {
				for (Products product : prodList) {
					if (product.getProdImgPath() != null) {

						String prodImgPath = product.getProdImgPath();
						if (prodImgPath.startsWith("/9j/")) {
							String imagePath = saveBase64Image(prodImgPath);
							String fullUrl = IMAGE_URL_PREFIX + imagePath;
							product.setProdImgPath(fullUrl);
						} else if (!prodImgPath.startsWith("/auction/images/")) {
							String relativePath = prodImgPath.replace(BASE_IMAGE_PATH, "");
							String fullUrl = IMAGE_URL_PREFIX + relativePath;
							product.setProdImgPath(fullUrl);
						}

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ResponseEntity.ok(prodList);
		}
	}

	@PostMapping(value = "/userBidItems", produces = "application/json")
	public ResponseEntity<List<Products>> userBidItems(@RequestBody Map<String, String> payload,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.substring(7);

		if (!jwtUtil.validateToken(token)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		String buyerId = payload.get("userId");
		List<Products> bidItems = service.findUserBidItems(buyerId);

		if (bidItems.isEmpty()) {
			return ResponseEntity.ok(Collections.emptyList()); // 빈 리스트를 반환
		}

		for (Products product : bidItems) {
			if (product.getProdImgPath() != null) {
				try {
					String prodImgPath = product.getProdImgPath();
					if (prodImgPath.startsWith("/9j/")) {
						String imagePath = saveBase64Image(prodImgPath);
						String fullUrl = IMAGE_URL_PREFIX + imagePath;
						product.setProdImgPath(fullUrl);
					} else if (!prodImgPath.startsWith("/auction/images/")) {
						String relativePath = prodImgPath.replace(BASE_IMAGE_PATH, "");
						String fullUrl = IMAGE_URL_PREFIX + relativePath;
						product.setProdImgPath(fullUrl);
					}
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				}
			}
		}

		return ResponseEntity.ok(bidItems);
	}

	// Base64 이미지를 파일로 저장하는 메서드
	private String saveBase64Image(String base64Image) throws IOException {
		UUID uuid = UUID.randomUUID();
		String imagePath = uuid.toString() + ".jpg";
		File outputFile = new File(BASE_IMAGE_PATH + imagePath);
		Base64Codec.makeFileWithString(base64Image, outputFile);
		return imagePath;
	}

	// Base64 이미지 업로드 메서드
	@PostMapping("/uploadBase64Image")
	public ResponseEntity<String> uploadBase64Image(@RequestBody String base64Image) {
		try {
			String imagePath = saveBase64Image(base64Image);
			String fullUrl = IMAGE_URL_PREFIX + imagePath;
			return ResponseEntity.ok(fullUrl);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
		}
	}

	// 상품 상세정보 조회 메서드 (LHGfeature 브랜치에서 추가)
	@PostMapping("/prodDetail")
	public Products prodDetail(@RequestParam("prodIdx") String idx) {
		int prodIdx = Integer.parseInt(idx);
		Products product = service.prodDetail(prodIdx);
		return product;
	}

	@PostMapping("/myProd")
	public ResponseEntity<List<Products>> myProd(@RequestParam("userId") String userId) {

		System.out.println(userId);
		List<Products> myProdList = service.myProd(userId);
		System.out.println(myProdList.get(0).getProdName());

		if (myProdList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(myProdList);
		} else {
			try {
				for (Products product : myProdList) {
					if (product.getProdImgPath() != null) {

						String prodImgPath = product.getProdImgPath();
						if (prodImgPath.startsWith("/9j/")) {
							String imagePath = saveBase64Image(prodImgPath);
							String fullUrl = IMAGE_URL_PREFIX + imagePath;
							product.setProdImgPath(fullUrl);
						} else if (!prodImgPath.startsWith("/auction/images/")) {
							String relativePath = prodImgPath.replace(BASE_IMAGE_PATH, "");
							String fullUrl = IMAGE_URL_PREFIX + relativePath;
							product.setProdImgPath(fullUrl);
						}

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ResponseEntity.ok(myProdList);
		}
	}

	@PostMapping("bid")
	public void bid(@RequestParam("userId") String userId, 
			@RequestParam("prodIdx") String prodIdx,
			@RequestParam("money") String money) {
		System.out.println(userId);
		System.out.println(prodIdx);
		System.out.println(money);
	}

	@PostMapping("buy")
	public void buy(@RequestParam("userId")String userId) {
		System.out.println(userId);
	}

	@PostMapping("/prodRegister")
	public ResponseEntity<String> registerProduct(@RequestBody Map<String, String> payload) {
		try {
			String prodName = payload.get("prodName");
			String prodInfo = payload.get("prodInfo");
			Long bidPrice = Long.valueOf(payload.get("bidPrice"));
			Long immediatePrice = Long.valueOf(payload.get("immediatePrice"));
			String userId = payload.get("userId");
			String base64Image = payload.get("prodImgPath");

			String imagePath = saveBase64Image(base64Image);

			Products product = new Products();
			product.setProdName(prodName);
			product.setProdInfo(prodInfo);
			product.setBidPrice(bidPrice);
			product.setImmediatePrice(immediatePrice);
			product.setUserId(userId);
			product.setBidStatus('N');
			product.setProdImgPath(IMAGE_URL_PREFIX + imagePath);

			service.saveProduct(product);

			return ResponseEntity.status(HttpStatus.CREATED).body("Product registered successfully");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to save image: " + e.getMessage());
		}
	}
}
