package com.davis.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * Represents a product entity in the system.
 * This class is used to store and manage information about products, including their SKU, barcode, name,
 * supplier, category, weight, unit of measurement, and other attributes.
 * 
 * The product SKU is automatically generated upon initialization using a combination of the product name,
 * category, weight, and a sequential number. The creation date and status are also set during initialization.
 * 
 * This class is annotated with JPA annotations to map it to a database table and define relationships
 * with other entities such as `Supplier`, `ProductCategory`, `PurchaseOrderItem`, `SalesItem`, `Inventory`,
 * and `ProductDiscount`.
 * 
 * @author CYPRIAN DAVIS
 * @version 1.1
 * @since 2024-10-01
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Product.getProducts", query = "SELECT p FROM Product p")
   
})
public class Product {
    @Autowired
    private IdGeneration idGeneration;

    @Id
    @Column(name="ProductCode")
    private String productSKU; // Unique identifier for the product (Stock Keeping Unit)

    private String barCode; // Barcode of the product

    @Column(name="ProductName")
    private String product; // Name of the product

    @ManyToOne
    @JoinColumn(name="supplier")
    private Supplier supplier; // Supplier of the product

    @ManyToOne
    @JoinColumn(name="Category")
    private ProductCategory category; // Category of the product

    private String description; // Description of the product

    @Column(name="weight")
    private double productWeight; // Weight of the product (e.g., 1.5 in liters or kilograms)

    
    private String unitOfmeasurement; // Unit of measurement for the product weight

    private boolean refrigerated; // Indicates whether the product needs to be refrigerated

    private int maximumStockUnit; // Maximum number of stock units for the product

    private String status; // Status of the product (e.g., ACTIVE, EXPIRED, DISCONTINUED)

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn; // Date when the product was added

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="lastUpdatedOn")
    private Date updatedOn; // Date when the product was last updated

    
    /**
     * Default constructor required by JPA.
     */
    public Product() {}

    /**
     * Generates a unique SKU (Stock Keeping Unit) for the product based on its attributes:
     * - First letter of the product name
     * - First letter of the product category
     * - Product weight (if available), followed by the first letter of the unit of measurement
     * - A sequential number to ensure the SKU is unique even for products with the same name/category
     * 
     * The SKU follows the format:
     * [ProductInitial]-[CategoryInitial]-[WeightUnit]-[SequentialNumber]
     * Example: A-F-1.5kg-001
     */
    private void generateSKU() {
        StringBuilder skuBuilder = new StringBuilder();

        // Step 1: Add the product initial
        skuBuilder.append(getProductInitial());

        // Step 2: Add the category initial
        skuBuilder.append(getCategoryInitial());

        // Step 3: Add the weight and unit of measurement (if available)
        skuBuilder.append(getWeightAndMeasurement());

        // Step 4: Add the sequential number
        skuBuilder.append(getSequentialNumber());

        // Step 5: Set the generated SKU to the product's productSKU field
        this.productSKU = skuBuilder.toString();
    }

    /**
     * Extracts the first letter of the product name and converts it to uppercase.
     * 
     * @return The product initial (e.g., "A" for "Apple").
     */
    private String getProductInitial() {
        if (product != null && !product.isEmpty()) {
            return product.substring(0, 1).toUpperCase();
        }
        return "";
    }

    /**
     * Extracts the first letter of the product category name and converts it to uppercase.
     * 
     * @return The category initial (e.g., "F" for "Fruits").
     */
    private String getCategoryInitial() {
        if (category != null && category.getName() != null && !category.getName().isEmpty()) {
            return category.getName().substring(0, 1).toUpperCase();
        }
        return "";
    }

    /**
     * Constructs the weight and unit of measurement part of the SKU.
     * 
     * @return The weight and unit of measurement (e.g., "-1.5kg").
     */
    private String getWeightAndMeasurement() {
        if (productWeight > 0) {
            StringBuilder weightBuilder = new StringBuilder("-").append(productWeight);

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
     * Initializes the product SKU, creation date, and status after the bean is constructed.
     * This method is automatically called by Spring after the bean is instantiated.
     */
    @PostConstruct
    public void initialize() {
        // Generate SKU after dependency injection
        generateSKU();

        // Set date of creation
        LocalDateTime now = LocalDateTime.now();
        this.createdOn = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        // Set status
        this.status = Status.ACTIVE.toString().toUpperCase();
    }

    // Getters and Setters for all fields

    /**
     * @return The product SKU.
     */
    public String getProductSKU() {
        return productSKU;
    }

    /**
     * @param productSKU The product SKU to set.
     */
    public void setProductSKU(String productSKU) {
        this.productSKU = productSKU;
    }

    /**
     * @return The barcode of the product.
     */
    public String getBarCode() {
        return barCode;
    }

    /**
     * @param barCode The barcode to set.
     */
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    /**
     * @return The name of the product.
     */
    public String getProduct() {
        return product;
    }

    /**
     * @param product The name of the product to set.
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * @return The supplier of the product.
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * @param supplier The supplier to set.
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * @return The category of the product.
     */
    public ProductCategory getCategory() {
        return category;
    }

    /**
     * @param category The category to set.
     */
    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    /**
     * @return The description of the product.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The weight of the product.
     */
    public double getproductWeight() {
        return productWeight;
    }

    /**
     * @param weight The weight to set.
     */
    public void setproductWeight(double weight) {
        this.productWeight = weight;
    }

    /**
     * @return The unit of measurement for the product weight.
     */
    public String getUnitOfmeasurement() {
        return unitOfmeasurement;
    }

    /**
     * @param unitOfmeasurement The unit of measurement to set.
     */
    public void setUnitOfmeasurement(String unitOfmeasurement) {
        this.unitOfmeasurement = unitOfmeasurement;
    }

    /**
     * @return Whether the product needs to be refrigerated.
     */
    public boolean isRefrigerated() {
        return refrigerated;
    }

    /**
     * @param refrigerated Whether the product needs to be refrigerated.
     */
    public void setRefrigerated(boolean refrigerated) {
        this.refrigerated = refrigerated;
    }

    /**
     * @return The maximum number of stock units for the product.
     */
    public int getMaximumStockUnit() {
        return maximumStockUnit;
    }

    /**
     * @param maximumStockUnit The maximum number of stock units to set.
     */
    public void setMaximumStockUnit(int maximumStockUnit) {
        this.maximumStockUnit = maximumStockUnit;
    }

    /**
     * @return The status of the product.
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The date when the product was added.
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn The creation date to set.
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @return The date when the product was last updated.
     */
    public Date getUpdatedOn() {
        return updatedOn;
    }

    /**
     * @param updatedOn The last update date to set.
     */
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}