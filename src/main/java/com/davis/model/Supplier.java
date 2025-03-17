package com.davis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Represents a supplier entity in the system.
 * A supplier can provide products, receive payments, and have purchase orders associated with them.
 * This class is mapped to a database table using JPA annotations.
 * 
 * @author CYPRIAN DAVIS
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Supplier.findAll",query = "SELECT s FROM Supplier s")
})
public class Supplier {
   
    @Id
    private String supplierId; // Unique identifier for the supplier

    @Column(name = "SupplierName")
    private String name; // Name of the supplier

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "phoneNumber", column = @Column(name = "contact")),
        @AttributeOverride(name = "contact", column = @Column(name = "otherContact"))
    })
    private ContactInfo contact; // Contact information of the supplier

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "Zip_Code", column = @Column(name = "Postal_Code")),
        @AttributeOverride(name = "city", column = @Column(name = "Street"))
    })
    private Address address; // Address information of the supplier

    
    private String createdOn; // Timestamp when the supplier was created

    @Column(name = "LastUpdatedOn")
    private String updatedOn; // Timestamp when the supplier was last updated

    private String status; // Current status of the supplier (e.g., ACTIVE, INACTIVE)

    @OneToMany(targetEntity = Product.class, mappedBy = "supplier", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Product> products = new HashSet<>(); // Set of products supplied by this supplier
    /**
     * Default constructor.
     */
    public Supplier() {
    }

    /**
     * Gets the supplier ID.
     *
     * @return The supplier ID.
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * Sets the supplier ID.
     *
     * @param supplierId The supplier ID to set.
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * Gets the supplier name.
     *
     * @return The supplier name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the supplier name.
     *
     * @param name The supplier name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the contact information of the supplier.
     *
     * @return The contact information.
     */
    public ContactInfo getContact() {
        return contact;
    }

    /**
     * Sets the contact information of the supplier.
     *
     * @param contact The contact information to set.
     */
    public void setContact(ContactInfo contact) {
        this.contact = contact;
    }

    /**
     * Gets the address information of the supplier.
     *
     * @return The address information.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address information of the supplier.
     *
     * @param address The address information to set.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets the set of products supplied by this supplier.
     *
     * @return The set of products.
     */
    public Set<Product> getProducts() {
        return products;
    }

    /**
     * Sets the set of products supplied by this supplier.
     *
     * @param products The set of products to set.
     */
    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    /**
     * Gets the creation timestamp of the supplier.
     *
     * @return The creation timestamp.
     */
    public String getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets the creation timestamp of the supplier.
     *
     * @param createdOn The creation timestamp to set.
     */
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Gets the last updated timestamp of the supplier.
     *
     * @return The last updated timestamp.
     */
    public String getUpdatedOn() {
        return updatedOn;
    }

    /**
     * Sets the last updated timestamp of the supplier.
     *
     * @param updatedOn The last updated timestamp to set.
     */
    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    /**
     * Gets the status of the supplier.
     *
     * @return The status of the supplier.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the supplier.
     *
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}