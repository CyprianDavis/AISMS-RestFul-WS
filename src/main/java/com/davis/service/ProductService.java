package com.davis.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.davis.dao.ProductDao;
import com.davis.model.IdGeneration;
import com.davis.model.Product;
import com.davis.model.ProductCategory;
import com.davis.model.Status;

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
    private IdGeneration idGeneration; // Utility for generating unique IDs

    @Autowired
    private ProductDao productDao; // Data access object for product-related operations

    /**
     * Saves a new product to the system.
     * Generates a unique SKU for the product, sets the creation date and status,
     * and then persists the product using the DAO.
     *
     * @param product The product to be saved.
     * @return The saved product.
     */
    public Product saveProduct(Product product) {
        // Generate the SKU
        String sku = generateSKU(product);
        product.setProductSKU(sku);

        // Set the creation date and status
        LocalDateTime now = LocalDateTime.now();
        product.setCreatedOn(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()).toString());
        product.setStatus(Status.ACTIVE.toString().toUpperCase());

        // Save the product using the DAO
        return productDao.addProduct(product);
    }

    /**
     * Saves a new product category to the system.
     * Generates a unique ID for the category, sets the creation date,
     * and then persists the category using the DAO.
     *
     * @param category The product category to be saved.
     * @return The saved product category.
     */
    public ProductCategory saveProductCategory(ProductCategory category) {
        // Generate a unique ID for the category
        long id = idGeneration.getNextIdNumber("productCategory");
        category.setId(generateProductCategoryId(id));

        // Set the creation date
        LocalDateTime now = LocalDateTime.now();
        category.setCreatedOn(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()).toString());

        // Save the category using the DAO
        return productDao.addProductCategory(category);
    }

    /**
     * Generates a unique SKU for the product.
     * The SKU is generated using the product name, category, weight, unit of measurement,
     * and a sequential number.
     *
     * @param product The product for which to generate the SKU.
     * @return The generated SKU.
     */
    private String generateSKU(Product product) {
        StringBuilder skuBuilder = new StringBuilder();

        // Step 1: Add the product initial
        skuBuilder.append(getProductInitial(product.getProduct()));

        // Step 2: Add the category initial
        skuBuilder.append(getCategoryInitial(product.getCategory()));

        // Step 3: Add the weight and unit of measurement (if available)
        skuBuilder.append(getWeightAndMeasurement(product.getproductWeight(), product.getUnitOfmeasurement()));

        // Step 4: Add the sequential number
        skuBuilder.append(getSequentialNumber());

        return skuBuilder.toString();
    }

    /**
     * Extracts the first letter of the product name and converts it to uppercase.
     *
     * @param productName The name of the product.
     * @return The product initial (e.g., "A" for "Apple").
     */
    private String getProductInitial(String productName) {
        if (productName != null && !productName.isEmpty()) {
            return productName.substring(0, 1).toUpperCase();
        }
        return "";
    }

    /**
     * Extracts the first letter of the product category name and converts it to uppercase.
     *
     * @param category The product category.
     * @return The category initial (e.g., "F" for "Fruits").
     */
    private String getCategoryInitial(ProductCategory category) {
        if (category != null && category.getName() != null && !category.getName().isEmpty()) {
            return category.getName().substring(0, 1).toUpperCase();
        }
        return "";
    }

    /**
     * Constructs the weight and unit of measurement part of the SKU.
     *
     * @param weight           The weight of the product.
     * @param unitOfmeasurement The unit of measurement.
     * @return The weight and unit of measurement (e.g., "-1.5kg").
     */
    private String getWeightAndMeasurement(double weight, String unitOfmeasurement) {
        if (weight > 0) {
            StringBuilder weightBuilder = new StringBuilder("-").append(weight);

            if (unitOfmeasurement != null && !unitOfmeasurement.isEmpty()) {
                if (unitOfmeasurement.length() == 1) {
                    weightBuilder.append(unitOfmeasurement.substring(0, 1));
                } else {
                    weightBuilder.append(unitOfmeasurement.substring(0, 2));
                }
            }
            return weightBuilder.toString();
        }
        return "";
    }

    /**
     * Generates a sequential number for the SKU and pads it to a 3-digit format.
     *
     * @return The sequential number (e.g., "-001").
     */
    private String getSequentialNumber() {
        long skuNumber = idGeneration.getNextIdNumber("skuNumber");

        if (skuNumber <= 9) {
            return "-" + String.format("%03d", skuNumber);
        } else if (skuNumber >= 10 && skuNumber <= 99) {
            return "-" + String.format("%02d", skuNumber);
        } else if (skuNumber >= 100) {
            return "-" + String.format("%01d", skuNumber);
        }
        return "";
    }

    /**
     * Generates a unique category ID based on the provided ID.
     * The ID is formatted to ensure it has a consistent length.
     *
     * @param id The unique sequence number for the category.
     * @return A formatted category ID string.
     */
    private String generateProductCategoryId(long id) {
        // Handle case where auto_id is less than 10
        if (id <= 9) {
            return "CAT00" + id;
        }
        // Handle case where auto_id is greater than or equal to 10 and less than 100
        else if (id >= 10 && id < 100) {
            return "CAT0" + id;
        }
        // Handle case where auto_id is greater than or equal to 100
        else {
            return "CATO" + id;
        }
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