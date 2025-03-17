package com.davis.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.davis.dao.ProductDao;
import com.davis.model.Product;
import com.davis.model.ProductCategory;

/**
 * Service class for managing product-related operations.
 * This class acts as an intermediary between the controller and the data access layer (DAO).
 * It handles business logic for managing products and product categories.
 *
 * @Service Indicates that this class is a Spring-managed service component.
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * Saves a new product to the system.
     *
     * @param product The product to be saved.
     * @return The saved product.
     */
    public Product saveProduct(Product product) {
        return productDao.addProduct(product);
    }

    /**
     * Retrieves a list of all products in the system.
     *
     * @return A list of products.
     */
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    /**
     * Retrieves a specific product by its SKU (Stock Keeping Unit).
     *
     * @param sku The SKU of the product to retrieve.
     * @return The product with the specified SKU, or null if not found.
     */
    public Product getProduct(String sku) {
        return productDao.getProduct(sku);
    }

    /**
     * Retrieves a list of all product categories in the system.
     *
     * @return A list of product categories.
     */
    public List<ProductCategory> getProductCategories() {
        return productDao.getProductCategories();
    }
}