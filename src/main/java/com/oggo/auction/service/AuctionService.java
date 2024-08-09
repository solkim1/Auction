package com.oggo.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.oggo.auction.repository.ProdRepository;

import jakarta.transaction.Transactional;

@Service
public class AuctionService {

    @Autowired
    private ProdRepository prodRepository;
    
    @Transactional
    @Scheduled(cron = "0 * * * * ?") // 매분마다 실행
    public void checkAndUpdateBidStatus() {
    	
    	/*  개발단계에서 서버가 하나만 켜져있는게 아닐경우 
    		분마다 실행하면 BLOCK 걸릴수 있는 문제때문에 주석처리해놓음
    		만약 진짜 배포하고 서버 1개로만 굴리는 상황이 오면 주석 해제할 것
    	*/
    	
    	// 
    	
//        prodRepository.updateExpiredBids();
        
    }
}
