package com.davis.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.davis.model.Product;
import com.davis.model.ProductCategory;
@Repository
@Transactional
public class ProductDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Product addProduct(Product product) {
		entityManager.persist(product);
		return product;
	}
	@Transactional(readOnly=true)
	public List<Product> getProducts(){
		return entityManager.createNamedQuery("Product.getProducts",Product.class).getResultList();
	}
	@Transactional(readOnly=true)
	public Product getProduct(String sku) {
		return entityManager.find(Product.class, sku);
	}
	@Transactional(readOnly=true)
	public List<ProductCategory> getProductCategories(){
		return entityManager.createNamedQuery("ProductCategory.getCategories",ProductCategory.class)
		.getResultList();
	}
	public ProductCategory addProductCategory(ProductCategory category) {
		 entityManager.persist(category);
		 return category;
	}

}
