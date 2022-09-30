package com.cts.purchase.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.purchase.entity.Purchase;

@Repository
public interface PurchaseDao extends JpaRepository<Purchase, Long>{

	public List<Purchase> findByBookId(Long id);
	
	public List<Purchase> findByUserId(Long id);
	
}
