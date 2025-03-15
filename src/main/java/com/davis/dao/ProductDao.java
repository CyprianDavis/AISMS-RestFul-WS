package com.davis.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.davis.model.Product;
import com.davis.model.ProductCategory;
@Transactional
@Repository
public class ProductDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Product addProduct(Product product) {
		sessionFactory.getCurrentSession().persist(product);
		return product;
	}
	@Transactional(readOnly=true)
	public List<Product> getProducts(){
		return sessionFactory.getCurrentSession().createNamedQuery("Product.getProducts",Product.class).getResultList();
	}
	@Transactional(readOnly=true)
	public Product getProduct(String sku) {
		return sessionFactory.getCurrentSession().find(Product.class, sku);
	}
	@Transactional(readOnly=true)
	public List<ProductCategory> getProductCategories(){
		return sessionFactory.getCurrentSession().createNamedQuery("ProductCategory.getCategories",ProductCategory.class)
		.getResultList();
	}
	

}
