package com.davis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


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
    private Double productWeight; // Weight of the product (e.g., 1.5 in liters or kilograms)

    
    private String unitOfmeasurement; // Unit of measurement for the product weight

    private boolean refrigerated; // Indicates whether the product needs to be refrigerated

    private int maximumStockUnit; // Maximum number of stock units for the product

    private String status; // Status of the product (e.g., ACTIVE, EXPIRED, DISCONTINUED)

  
    private String createdOn; // Date when the product was added

    
    @Column(name="lastUpdatedOn")
    private String updatedOn; // Date when the product was last updated

    
    /**
     * Default constructor required by JPA.
     */
    public Product() {}

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
    public Double getproductWeight() {
        return productWeight;
    }

    /**
     * @param weight The weight to set.
     */
    public void setproductWeight(Double weight) {
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
    public String getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn The creation date to set.
     */
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @return The date when the product was last updated.
     */
    public String getUpdatedOn() {
        return updatedOn;
    }

    /**
     * @param updatedOn The last update date to set.
     */
    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }
}