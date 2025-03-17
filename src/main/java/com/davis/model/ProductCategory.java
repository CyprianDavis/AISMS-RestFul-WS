package com.davis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * Represents a product category entity in the system.
 * This class is used to store and manage information about product categories, including their ID, name,
 * description, creation date, and associated products.
 * 
 * 
 * This class is annotated with JPA annotations to map it to a database table and define relationships
 * with other entities such as `Product`.
 * 
 * @author CYPRIAN DAVIS
 * @version 1.1
 * @since 2024-10-01
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ProductCategory.getCategories", query="SELECT p FROM ProductCategory p")
    
})
public class ProductCategory {
   

    @Id
    @Column(name="CategoryId")
    private String id; // Unique identifier for the product category

    private String name; // Name of the category

    private String description; // Description of the category
    
    private String createdOn; // Date when the category was created
    @Column(name="lastUpdatedOn")
   
    private String updatedOn; // Date when the category was last updated

    // One-to-many relationship with Product
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    /**
     * Default constructor required by JPA.
     */
    public ProductCategory() {}

    /**
     * @return The unique identifier for the category.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The unique identifier to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name of the category to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The description of the category.
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
     * @return The date when the category was created.
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
     * @return The date when the category was last updated.
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

    /**
     * @return The set of products associated with the category.
     */
    public Set<Product> getProducts() {
        return products;
    }

    /**
     * @param products The set of products to set.
     */
    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}