package com.davis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.davis.model.Product;
import com.davis.model.ProductCategory;
import com.davis.service.ProductService;

@Controller
@RequestMapping(value="/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/products", method =RequestMethod.GET)
	@ResponseBody
	public List<Product> products(){
		return productService.getProducts();
	}
	@RequestMapping(value="/category",method=RequestMethod.GET)
	@ResponseBody
	public List<ProductCategory> productCategory(){
		return productService.getProductCategories();
	}
	@RequestMapping(value="/{sku}",method=RequestMethod.GET)
	@ResponseBody
	public Product getProduct(@PathVariable  String sku) {
		return productService.getProduct(sku);
	}
	@RequestMapping(value="/",method =RequestMethod.POST)
	@ResponseBody
	public Product addProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

}
