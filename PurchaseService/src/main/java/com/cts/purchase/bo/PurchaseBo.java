package com.cts.purchase.bo;

import java.util.List;

import com.cts.purchase.entity.Purchase;
import com.cts.purchase.exception.DigitalBooksException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface PurchaseBo {
	
	public Purchase addPurchase(Purchase purchase);
	
	public Purchase updatePurchase(Purchase purchase);
	
	public List<Purchase> findPurchaseByBookId(Long id);
	
	public List<Purchase> findPurchaseByUserId(Long id);
	
	public Purchase findPurchaseById(Long id) throws DigitalBooksException;

	public void sendPurchaseNotification(Purchase purchase) throws JsonProcessingException;

}
