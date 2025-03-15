package com.davis.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.davis.dao.InventoryDao;
import com.davis.model.Inventory;

@Repository
@Transactional
public class InventoryService {
	@Autowired
	private InventoryDao inventoryDao;
	
	public Inventory addInventory(Inventory inventory) {
		return inventoryDao.addInventory(inventory);
	}
	public List<Inventory> getInventory(){
		return inventoryDao.getInventory();
		
	}

}
