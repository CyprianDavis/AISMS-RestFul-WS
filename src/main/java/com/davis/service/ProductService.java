package com.davis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davis.dao.ProductDao;
import com.davis.model.Product;
import com.davis.model.ProductCategory;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	
	public Product saveProduct(Product product) {
		return productDao.addProduct(product);
	}
	public List<Product> getProducts(){
		return productDao.getProducts();
	}
	
	public Product getProduct(String sku) {
		return productDao.getProduct(sku);
		
	}
	public List <ProductCategory> getProductCategories(){
		return productDao.getProductCategories();
		
	}
	

}
