package com.oggo.auction.controller;

import java.io.File;
import java.io.IOException;
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
    private final String IMAGE_URL_PREFIX = "http://192.168.137.1:8089/auction/images/";

    // 상품 목록 조회 메서드
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
                            // Base64 데이터로부터 파일 생성
                            String imagePath = saveBase64Image(prodImgPath);
                            String fullUrl = IMAGE_URL_PREFIX + imagePath;
                            product.setProdImgPath(fullUrl);
                        } else if (!prodImgPath.startsWith("http://") && !prodImgPath.startsWith("https://")) {
                            // 파일 경로를 URL로 변환
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

    // 사용자 입찰 상품 조회 메서드
    @PostMapping(value = "/userBidItems", produces = "application/json")
    public ResponseEntity<List<Products>> userBidItems(@RequestBody Map<String, String> payload, @RequestHeader("Authorization") String authHeader) {
        // JWT 토큰 검증 (보안 강화)
        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        String buyerId = payload.get("userId");
        List<Products> bidItems = service.findUserBidItems(buyerId);

        if (bidItems.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // 이미지 처리 로직
        for (Products product : bidItems) {
            if (product.getProdImgPath() != null) {
                try {
                    String prodImgPath = product.getProdImgPath();
                    if (prodImgPath.startsWith("/9j/")) {
                        // Base64 데이터로부터 파일 생성
                        String imagePath = saveBase64Image(prodImgPath);
                        String fullUrl = IMAGE_URL_PREFIX + imagePath;
                        product.setProdImgPath(fullUrl);
                    } else if (!prodImgPath.startsWith("http://") && !prodImgPath.startsWith("https://")) {
                        // 파일 경로를 URL로 변환
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
    @PostMapping(value="/prodDetail")
    public Products prodDetail(@RequestParam("prodIdx") int prodIdx) {
        System.out.println(prodIdx);
        Products product = service.prodDetail(prodIdx);
        System.out.println(product.getProdName());
        return product;
    }
}