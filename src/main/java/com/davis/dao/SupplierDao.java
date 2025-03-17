package com.davis.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.davis.model.Supplier;

@Repository
@Transactional
public class SupplierDao {
	@PersistenceContext
	private EntityManager entityManager;
	public Supplier addSupplier(Supplier supplier) {
		entityManager.persist(supplier);
		return supplier;
	}
	@Transactional(readOnly=true)
	public List<Supplier> getSupplies(){
		return entityManager.createNamedQuery("Supplier.findAll",Supplier.class)
		.getResultList();
	}
	public Supplier getSupplier(String id) {
		return entityManager.find(Supplier.class, id);
	}
}
