package com.davis.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.davis.model.Inventory;

@Repository
@Transactional
public class InventoryDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Inventory addInventory(Inventory inventory) {
		entityManager.persist(inventory);
		return inventory;
	}
	@Transactional(readOnly=true)
	public List<Inventory> getInventory(){
		return entityManager.createNamedQuery("Inventory.viewInventory", Inventory.class)
		.getResultList();
		
	}

}
