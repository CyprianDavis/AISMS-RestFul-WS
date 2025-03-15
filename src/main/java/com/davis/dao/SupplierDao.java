package com.davis.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.davis.model.Supplier;

@Repository
@Transactional
public class SupplierDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Supplier addSupplier(Supplier supplier) {
		sessionFactory.getCurrentSession().persist(supplier);
		return supplier;
	}
	public List<Supplier> getSupplies(){
		return sessionFactory.getCurrentSession().createNamedQuery("Supplier.findAll",Supplier.class)
		.getResultList();
	}
	public Supplier getSupplier(String id) {
		return sessionFactory.getCurrentSession().find(Supplier.class, id);
	}
}
