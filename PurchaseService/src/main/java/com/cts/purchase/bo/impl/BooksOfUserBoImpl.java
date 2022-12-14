package com.cts.purchase.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.purchase.bo.PurchaseBo;
import com.cts.purchase.dao.PurchaseDao;
import com.cts.purchase.entity.Purchase;
import com.cts.purchase.exception.DigitalBooksException;

@Service
public class BooksOfUserBoImpl implements PurchaseBo{
	
	@Autowired
	private PurchaseDao dao;

	@Override
	public Purchase addPurchase(Purchase booksofUser) {
		return dao.saveAndFlush(booksofUser);
	}

	@Override
	public Purchase updatePurchase(Purchase purchase) {
		findPurchaseByBookId(purchase.getBooking_id());
		return dao.saveAndFlush(purchase);
	}

	@Override
	public List<Purchase> findPurchaseByBookId(Long id) {
		return dao.findByBookId(id);
	}

	@Override
	public List<Purchase> findPurchaseByUserId(Long id) {
		return dao.findByUserId(id);
	}

	@Override
	public Purchase findPurchaseById(Long id) throws DigitalBooksException {
		Purchase bu= dao.findById(id).get();
		if(bu==null) {
			throw new DigitalBooksException("No purcase found with id:"+id);
		}
		return bu;
	}

}
