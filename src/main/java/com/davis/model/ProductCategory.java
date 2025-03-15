package com.davis.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;



/**
 * Represents a product category entity in the system.
 * This class is used to store and manage information about product categories, including their ID, name,
 * description, creation date, and associated products.
 * 
 * The category ID is automatically generated upon initialization using a unique sequence number.
 * The creation date is also set during initialization.
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
    @NamedQuery(name="ProductCategory.getCategories", query="SELECT p FROM ProductCategory p"),
    
})
public class ProductCategory {
    @Autowired
    private IdGeneration idGeneration;

    @Id
    @Column(name="CategoryId")
    private String id; // Unique identifier for the product category

    private String name; // Name of the category

    private String description; // Description of the category
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn; // Date when the category was created
    @Column(name="lastUpdatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn; // Date when the category was last updated

    // One-to-many relationship with Product
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

    /**
     * Initializes the category ID and creation date after the bean is constructed.
     * This method is automatically called by Spring after the bean is instantiated.
     */
    @PostConstruct
    public void initializeCustomerId() {
        // Generate the category ID before the bean is fully initialized
        long auto_id = idGeneration.getNextIdNumber("productCategory");
        this.id = generateProductId(auto_id);

        // Set date of creation
        LocalDateTime now = LocalDateTime.now();
        this.createdOn = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Generates a unique category ID based on the provided ID.
     * 
     * @param id The unique sequence number for the category.
     * @return A formatted category ID string.
     */
    private String generateProductId(long id) {
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
     * @return The date when the category was last updated.
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