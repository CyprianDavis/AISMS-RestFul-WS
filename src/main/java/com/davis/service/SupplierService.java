package com.davis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davis.dao.SupplierDao;
import com.davis.model.Supplier;

@Service
public class SupplierService {
	@Autowired
	private SupplierDao supplierDao;
	
	public Supplier addSupllier(Supplier supplier) {
		return supplierDao.addSupplier(supplier);
	}
	public List<Supplier> getSupplies(){
		return  supplierDao.getSupplies();
	}
	public Supplier getSupplier(String id) {
		return supplierDao.getSupplier(id);
	}

}
