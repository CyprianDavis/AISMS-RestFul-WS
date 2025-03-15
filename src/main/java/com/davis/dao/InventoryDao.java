package com.davis.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.davis.model.Inventory;

@Repository
@Transactional
public class InventoryDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Inventory addInventory(Inventory inventory) {
		sessionFactory.getCurrentSession().persist(inventory);
		return inventory;
	}
	@Transactional(readOnly=true)
	public List<Inventory> getInventory(){
		return sessionFactory.getCurrentSession().createNamedQuery("Supplier.findAll", Inventory.class)
		.getResultList();
		
	}

}
